package com.macan.cqrs.core.producers;

import com.macan.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
