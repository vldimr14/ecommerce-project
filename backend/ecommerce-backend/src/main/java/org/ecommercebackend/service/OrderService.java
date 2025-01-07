package org.ecommercebackend.service;

import org.ecommercebackend.dtos.OrderDTO;
import org.ecommercebackend.dtos.OrderMapper;
import org.ecommercebackend.exceptions.NotEnoughStockException;
import org.ecommercebackend.exceptions.OrderNotFoundException;
import org.ecommercebackend.exceptions.ProductNotFoundException;
import org.ecommercebackend.exceptions.UserNotFoundException;
import org.ecommercebackend.models.Order;
import org.ecommercebackend.models.OrderProduct;
import org.ecommercebackend.models.Product;
import org.ecommercebackend.models.PromoCode;
import org.ecommercebackend.repositories.OrderRepository;
import org.ecommercebackend.repositories.ProductRepository;
import org.ecommercebackend.repositories.PromoCodeRepository;
import org.ecommercebackend.requests.OrderRequest;
import org.ecommercebackend.security.User;
import org.ecommercebackend.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PromoCodeRepository promoCodeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository,
                        PromoCodeRepository promoCodeRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.promoCodeRepository = promoCodeRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> dtos = new ArrayList<>();

        for (Order order : orders) {
            dtos.add(OrderMapper.toOrderDTO(order));
        }

        return dtos;
    }

    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found!"));
        return OrderMapper.toOrderDTO(order);
    }

    public OrderDTO create(Principal principal, List<OrderRequest> orderRequests, String promoCodeString) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException("Customer not found!"));

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setCustomer(user);
        order.setStatus("Created");

        PromoCode promoCode = null;

        // Check if customer provided a promo code
        if (promoCodeString != null) {
            Optional<PromoCode> promoCodeOptional = promoCodeRepository.findByCode(promoCodeString);
            if (promoCodeOptional.isPresent()) {
                promoCode = promoCodeOptional.get();
                order.setPromoCode(promoCode);
            }
        }

        BigDecimal totalAmount = BigDecimal.valueOf(0.0);

        for (OrderRequest request: orderRequests) {
            Product product = productRepository.findProductById(request.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found!"));

            if (product.getStockQuantity() < request.getQuantity()) {
                throw new NotEnoughStockException("Not enough stock for product: " + product.getName());
            }

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setPrice(product.getPrice());
            orderProduct.setQuantity(request.getQuantity());

            // Update stock quantity
            product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
            productRepository.save(product);

            order.getOrderProducts().add(orderProduct);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        }

        if (promoCode != null) {
            totalAmount = totalAmount.subtract(
                    totalAmount.multiply(
                            BigDecimal.valueOf(promoCode.getDiscount()).divide(BigDecimal.valueOf(100))
                    )
            );
        }

        order.setTotalPrice(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toOrderDTO(savedOrder);
    }

    public OrderDTO update(Order order) {
        Order updatedOrder = orderRepository.save(order);
        return OrderMapper.toOrderDTO(updatedOrder);
    }
}
