package com.client.management.services.impl;

import com.client.management.configs.ApplicationConfig;
import com.client.management.dto.messages.ClientMessage;
import com.client.management.enums.OperationType;
import com.client.management.services.ClientEventService;
import com.client.management.wrappers.ClientWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.client.management.utils.Constants.APPLICATION;
import static com.client.management.utils.Constants.KAFKA_SEND_MESSAGE;
import static com.client.management.utils.Constants.KAFKA_SUCCESS_MESSAGE;
import static com.client.management.utils.MethodUtil.getCurrentTimeStamp;
import static com.client.management.utils.MethodUtil.getFormattedCurrentTimeStamp;
import static com.client.management.utils.MethodUtil.getTraceId;

@Service
@AllArgsConstructor
@Slf4j
public class ClientEventServiceImpl implements ClientEventService {

    private final ApplicationConfig applicationConfig;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, ClientMessage> clientkafkaTemplate;

    @Override
    public void sendMessage(final ClientWrapper clientWrapper) {
        try {
            final ClientMessage clientMessage = buildClientMessage(clientWrapper);
            log.info(KAFKA_SEND_MESSAGE, applicationConfig.getTopicName(), clientMessage.getTraceId(),
                    objectMapper.writeValueAsString(clientMessage));
            clientkafkaTemplate.send(applicationConfig.getTopicName(), clientMessage.getTraceId(), clientMessage)
                    .whenCompleteAsync((result, throwable) -> {
                        if (throwable != null) {
                            log.error(throwable.getMessage(), throwable);
                            return;
                        }
                        log.info(KAFKA_SUCCESS_MESSAGE, applicationConfig.getTopicName(), clientMessage.getTraceId());
                    });
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    private ClientMessage buildClientMessage(final ClientWrapper clientWrapper) throws JsonProcessingException {
        final Long timestamp = getCurrentTimeStamp();
        final String traceId = getTraceId(clientWrapper.getClientHeader().getTraceParent());
        final String transactionCode = clientWrapper.getOperation() == OperationType.POST
                ? applicationConfig.getStatusCodePost()
                : applicationConfig.getStatusCodeGet();
        final var clientMessage = ClientMessage.builder()
                .analyticsTraceSource(APPLICATION + clientWrapper.getClientHeader().getConsumerId())
                .applicationId(clientWrapper.getClientHeader().getConsumerId())
                .region(applicationConfig.getRegion())
                .statusCode(applicationConfig.getStatusCodeError())
                .traceId(traceId)
                .timestamp(timestamp)
                .currentDate(getFormattedCurrentTimeStamp(timestamp))
                .transactionCode(transactionCode);
        if (clientWrapper.getClientRequest() != null) {
            clientMessage.inbound(objectMapper.writeValueAsString(clientWrapper.getClientRequest()));
        }
        if (clientWrapper.getClientResponse() != null) {
            clientMessage.outbound(objectMapper.writeValueAsString(clientWrapper.getClientResponse()));
            clientMessage.statusCode(applicationConfig.getStatusCodeSuccess());
        }
        return clientMessage.build();
    }
}