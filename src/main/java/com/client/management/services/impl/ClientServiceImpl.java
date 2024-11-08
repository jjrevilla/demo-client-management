package com.client.management.services.impl;

import com.client.management.dto.headers.ClientHeader;
import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import com.client.management.exceptions.ResourceClientException;
import com.client.management.repositories.ClientRepository;
import com.client.management.services.ClientService;
import com.client.management.utils.BuilderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Mono<ClientResponse> createClient(final ClientHeader clientHeader, final ClientRequest clientRequest) {
        return clientRepository.save(BuilderUtil.buildClient(clientRequest))
                .map(BuilderUtil::buildClientResponse)
                .onErrorMap(throwable -> {
                    throw new ResourceClientException(throwable.getMessage());
                });
    }

    @Override
    public Flux<ClientResponse> listClients(ClientHeader clientHeader) {
        return clientRepository.findAll()
                .map(BuilderUtil::buildClientResponse)
                .onErrorMap(throwable -> {
                    throw new ResourceClientException(throwable.getMessage());
                });
    }
}