package com.client.management.controllers;

import com.client.management.dto.headers.ClientHeader;
import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import com.client.management.enums.DeviceType;
import com.client.management.services.ClientService;
import com.client.management.validators.EnumValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.client.management.utils.Constants.CLIENTS_PATH;
import static com.client.management.utils.Constants.CONSUMER_ID_HEADER;
import static com.client.management.utils.Constants.CONSUMER_ID_INVALID_MESSAGE;
import static com.client.management.utils.Constants.CONSUMER_ID_PATTERN;
import static com.client.management.utils.Constants.DEVICE_ID_HEADER;
import static com.client.management.utils.Constants.DEVICE_ID_INVALID_MESSAGE;
import static com.client.management.utils.Constants.DEVICE_TYPE_HEADER;
import static com.client.management.utils.Constants.DEVICE_TYPE_INVALID_MESSAGE;
import static com.client.management.utils.Constants.TRACE_PARENT_HEADER;
import static com.client.management.utils.Constants.TRACE_PARENT_INVALID_MESSAGE;
import static com.client.management.utils.Constants.TRACE_PARENT_PATTERN;

@RestController
@AllArgsConstructor
@RequestMapping(CLIENTS_PATH)
public class ClientManagementController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientResponse> createClient(
            @RequestHeader(CONSUMER_ID_HEADER)
            @Valid @Pattern(regexp = CONSUMER_ID_PATTERN, message = CONSUMER_ID_INVALID_MESSAGE) final String consumerId,
            @RequestHeader(TRACE_PARENT_HEADER)
            @Valid @Pattern(regexp = TRACE_PARENT_PATTERN, message = TRACE_PARENT_INVALID_MESSAGE) final String traceParent,
            @RequestHeader(DEVICE_TYPE_HEADER)
            @Valid @EnumValidator(enumClass = DeviceType.class, message = DEVICE_TYPE_INVALID_MESSAGE) final String deviceType,
            @RequestHeader(DEVICE_ID_HEADER)
            @Valid @NotBlank(message = DEVICE_ID_INVALID_MESSAGE) final String deviceId,
            @RequestBody @Valid final ClientRequest clientRequest) {
        final ClientHeader clientHeader = ClientHeader.builder()
                .consumerId(consumerId)
                .traceParent(traceParent)
                .deviceType(deviceType)
                .deviceId(deviceId)
                .build();
        return clientService.createClient(clientHeader, clientRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ClientResponse> listClients(
            @RequestHeader(CONSUMER_ID_HEADER)
            @Valid @Pattern(regexp = CONSUMER_ID_PATTERN, message = CONSUMER_ID_INVALID_MESSAGE) final String consumerId,
            @RequestHeader(TRACE_PARENT_HEADER)
            @Valid @Pattern(regexp = TRACE_PARENT_PATTERN, message = TRACE_PARENT_INVALID_MESSAGE) final String traceParent,
            @RequestHeader(DEVICE_TYPE_HEADER)
            @Valid @EnumValidator(enumClass = DeviceType.class, message = DEVICE_TYPE_INVALID_MESSAGE) final String deviceType,
            @RequestHeader(DEVICE_ID_HEADER)
            @Valid @NotBlank(message = DEVICE_ID_INVALID_MESSAGE) final String deviceId) {
        final ClientHeader clientHeader = ClientHeader.builder()
                .consumerId(consumerId)
                .traceParent(traceParent)
                .deviceType(deviceType)
                .deviceId(deviceId)
                .build();
        return clientService.listClients(clientHeader);
    }
}