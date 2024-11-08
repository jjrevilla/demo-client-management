package com.client.management.utils;

import com.client.management.dto.requests.ClientRequest;
import com.client.management.dto.responses.ClientResponse;
import com.client.management.models.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.client.management.utils.MethodUtil.getCurrentTimeStamp;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuilderUtil {

    private static final String SPACE = " ";

    public static Client buildClient(final ClientRequest clientRequest) {
        final var clientBuilder = Client.builder()
                .name(clientRequest.getName())
                .fatherLastName(clientRequest.getFatherLastName())
                .documentType(clientRequest.getDocumentType())
                .documentNumber(clientRequest.getDocumentNumber())
                .status(Boolean.TRUE)
                .createdTimestamp(getCurrentTimeStamp());
        if (isNotBlank(clientRequest.getMotherLastName())) {
            clientBuilder.motherLastName(clientRequest.getMotherLastName());
        }
        return clientBuilder.build();
    }

    public static ClientResponse buildClientResponse(final Client client) {
        return ClientResponse.builder()
                .clientId(client.getId())
                .fullName(getFullName(client))
                .build();
    }

    private static String getFullName(final Client client) {
        final StringBuilder builder = new StringBuilder();
        builder.append(client.getName());
        builder.append(SPACE);
        builder.append(client.getFatherLastName());
        if (isNotBlank(client.getMotherLastName())) {
            builder.append(SPACE);
            builder.append(client.getMotherLastName());
        }
        return builder.toString();
    }
}