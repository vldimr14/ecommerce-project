package org.ecommercebackend.controllers;

import org.ecommercebackend.models.Order;
import org.ecommercebackend.requests.OrderRequest;
import org.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestParam Long customerId, @RequestBody List<OrderRequest> orderRequest) {
        return new ResponseEntity<>(orderService.create(customerId, orderRequest), HttpStatus.CREATED);
    }
}
