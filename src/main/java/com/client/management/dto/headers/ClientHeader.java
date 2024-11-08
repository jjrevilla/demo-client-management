package com.client.management.dto.headers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientHeader {

    private String consumerId;

    private String traceParent;

    private String deviceType;

    private String deviceId;
}