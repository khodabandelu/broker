package com.macan.account.cmd.infrastructure;

import com.macan.cqrs.core.events.BaseEvent;
import com.macan.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {
   @Autowired
   private KafkaTemplate<String,Object> kafkaTemplate;


    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic,event);
    }
}
