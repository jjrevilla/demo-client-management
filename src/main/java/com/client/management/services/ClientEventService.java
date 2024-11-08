package com.client.management.services;

import com.client.management.wrappers.ClientWrapper;

public interface ClientEventService {

    void sendMessage(final ClientWrapper clientWrapper);
}