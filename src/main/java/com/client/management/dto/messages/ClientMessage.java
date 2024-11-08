package com.client.management.dto.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ClientMessage {

    private String analyticsTraceSource;

    private String applicationId;

    private String region;

    private String statusCode;

    private String currentDate;

    private Long timestamp;

    private String traceId;

    private String inbound;

    private String outbound;

    private String transactionCode;
}