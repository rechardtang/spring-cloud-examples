package com.example.redispubsubpublisher.queue;

public interface MessagePublisher {
    void publish(final String message);
}
