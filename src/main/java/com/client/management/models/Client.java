package com.client.management.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.CosmosUniqueKey;
import com.azure.spring.data.cosmos.core.mapping.CosmosUniqueKeyPolicy;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import static com.client.management.utils.Constants.CONTAINER_CLIENT_NAME;
import static com.client.management.utils.Constants.CONTAINER_UNIQUE_KEY_PATH;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Container(containerName = CONTAINER_CLIENT_NAME)
@CosmosUniqueKeyPolicy(uniqueKeys = {@CosmosUniqueKey(paths = {CONTAINER_UNIQUE_KEY_PATH})})
public class Client {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String fatherLastName;

    private String motherLastName;

    @PartitionKey
    private String documentType;

    private String documentNumber;

    private Boolean status;

    private Long createdTimestamp;
}