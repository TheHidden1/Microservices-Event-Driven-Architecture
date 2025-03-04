package com.microservice.order_service.controller;

import com.microservice.base_domains.dto.Order;
import com.microservice.base_domains.dto.OrderEvent;
import com.microservice.order_service.kafka.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000/")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping()
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order placed");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return ResponseEntity.ok("Order placed successfully");
    }
}
