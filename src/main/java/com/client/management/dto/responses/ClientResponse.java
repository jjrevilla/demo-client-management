package com.client.management.dto.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientResponse {

    private String clientId;

    private String fullName;
}