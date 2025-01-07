package org.ecommercebackend.controllers;

import org.ecommercebackend.dtos.OrderDTO;
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
    public ResponseEntity<OrderDTO> createOrder(Principal principal, @RequestBody List<OrderRequest> orderRequest, @RequestParam(required = false) String promoCode) {
        return new ResponseEntity<>(orderService.create(principal, orderRequest, promoCode), HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
}
