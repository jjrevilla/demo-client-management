package com.client.management.services.impl;

import com.client.management.dto.headers.ClientHeader;
import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import com.client.management.enums.OperationType;
import com.client.management.exceptions.ResourceClientException;
import com.client.management.repositories.ClientRepository;
import com.client.management.services.ClientEventService;
import com.client.management.services.ClientService;
import com.client.management.utils.BuilderUtil;
import com.client.management.wrappers.ClientWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientEventService clientEventService;

    @Override
    public Mono<ClientResponse> createClient(final ClientHeader clientHeader, final ClientRequest clientRequest) {
        final var clientWrapper = ClientWrapper.builder()
                .clientHeader(clientHeader)
                .clientRequest(clientRequest)
                .operation(OperationType.POST);
        return clientRepository.save(BuilderUtil.buildClient(clientRequest))
                .map(BuilderUtil::buildClientResponse)
                .flatMap(clientResponse -> {
                    CompletableFuture.runAsync(() -> {
                        clientWrapper.clientResponse(clientResponse);
                        clientEventService.sendMessage(clientWrapper.build());
                    });
                    return Mono.just(clientResponse);
                })
                .onErrorMap(throwable -> {
                    CompletableFuture.runAsync(() -> clientEventService.sendMessage(clientWrapper.build()));
                    throw new ResourceClientException(throwable.getMessage());
                });
    }

    @Override
    public Flux<ClientResponse> listClients(ClientHeader clientHeader) {
        final var clientWrapper = ClientWrapper.builder()
                .clientHeader(clientHeader)
                .operation(OperationType.GET);
        return clientRepository.findAll()
                .map(BuilderUtil::buildClientResponse)
                .onErrorMap(throwable -> {
                    CompletableFuture.runAsync(() -> clientEventService.sendMessage(clientWrapper.build()));
                    throw new ResourceClientException(throwable.getMessage());
                });
    }
}