package org.ecommercebackend.controllers;

import org.ecommercebackend.models.Order;
import org.ecommercebackend.requests.OrderRequest;
import org.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(Principal principal, @RequestBody List<OrderRequest> orderRequest) {
        return new ResponseEntity<>(orderService.create(principal, orderRequest), HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
}
