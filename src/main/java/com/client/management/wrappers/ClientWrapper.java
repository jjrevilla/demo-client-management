package com.client.management.wrappers;

import com.client.management.dto.headers.ClientHeader;
import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import com.client.management.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ClientWrapper {

    private ClientHeader clientHeader;

    private ClientRequest clientRequest;

    private ClientResponse clientResponse;

    private OperationType operation;
}