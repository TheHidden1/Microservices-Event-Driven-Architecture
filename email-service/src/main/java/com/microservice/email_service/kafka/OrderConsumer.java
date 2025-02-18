package com.microservice.email_service.kafka;

import com.microservice.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @Bean
    public Consumer<OrderEvent> orderEventConsumer() {
        return orderEvent -> {
            logger.info("Received OrderEvent in Email Service: {}", orderEvent);
            sendEmailToCustomer(orderEvent);
        };
    }

    private void sendEmailToCustomer(OrderEvent orderEvent) {
        logger.info("Sending email for Order ID: {} ",
                orderEvent.getOrder().getOrderId());
    }
}
