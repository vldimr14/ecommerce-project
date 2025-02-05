package org.ecommercebackend.dtos;

import org.ecommercebackend.models.Order;
import org.ecommercebackend.models.OrderProduct;
import org.ecommercebackend.models.PromoCode;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setDateCreated(order.getDateCreated().toString());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderProducts(order.getOrderProducts().stream().map(OrderMapper::toOrderProductDTO)
                .collect(Collectors.toList()));
        dto.setPaid(order.isPaid());
        dto.setDeliveryMethod(order.getDeliveryMethod());
        dto.setContactPhoneNumber(order.getContactPhoneNumber());
        dto.setAddress(order.getAddress());

        if (order.getPromoCode() != null) {
            dto.setPromoCode(toPromoCodeDTO(order.getPromoCode()));
        }

        return dto;
    }

    public static OrderProductDTO toOrderProductDTO(OrderProduct orderProduct) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setOrderProductId(orderProduct.getOrderProductId());
        dto.setProductId(orderProduct.getProduct().getId());
        dto.setProductName(orderProduct.getProduct().getName());
        dto.setProductPrice(orderProduct.getPrice());
        dto.setQuantity(orderProduct.getQuantity());

        return dto;
    }

    public static PromoCodeDTO toPromoCodeDTO(PromoCode promoCode) {
        PromoCodeDTO dto = new PromoCodeDTO();
        dto.setCode(promoCode.getCode());
        dto.setDiscount(promoCode.getDiscount());

        return dto;
    }
}
