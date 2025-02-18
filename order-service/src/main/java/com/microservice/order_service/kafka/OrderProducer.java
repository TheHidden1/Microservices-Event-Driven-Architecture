package com.microservice.order_service.kafka;

import com.microservice.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private final StreamBridge streamBridge;

    public OrderProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendMessage(OrderEvent orderEvent) {
        LOGGER.info("Sending message via Spring Cloud Stream: {}", orderEvent);

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .build();

        streamBridge.send("orderOutput-out-0", message);
    }
}
