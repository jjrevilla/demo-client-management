package com.client.management.repositories;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.client.management.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCosmosRepository<Client, String> {
}