package org.ecommercebackend.service;

import org.ecommercebackend.models.Order;
import org.ecommercebackend.models.OrderProduct;
import org.ecommercebackend.models.Product;
import org.ecommercebackend.repositories.OrderRepository;
import org.ecommercebackend.repositories.ProductRepository;
import org.ecommercebackend.requests.OrderRequest;
import org.ecommercebackend.security.User;
import org.ecommercebackend.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        return order.orElse(null);
    }

    public Order create(Long customerId, List<OrderRequest> orderRequests) {
        User user = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setCustomer(user);

        BigDecimal totalAmount = BigDecimal.valueOf(0.0);

        for (OrderRequest request: orderRequests) {
            Product product = productRepository.findProductById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found!"));

            if (product.getStockQuantity() < request.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            OrderProduct orderProduct = new OrderProduct(order, product, request.getQuantity(), product.getPrice());

            product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
            productRepository.save(product);

            order.getOrderProducts().add(orderProduct);

            totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        }

        order.setTotalPrice(totalAmount);

        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
