package com.client.management.services;

import com.client.management.dto.headers.ClientHeader;
import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ClientResponse> createClient(final ClientHeader clientHeader, final ClientRequest clientRequest);

    Flux<ClientResponse> listClients(final ClientHeader clientHeader);
}