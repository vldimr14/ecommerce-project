package org.ecommercebackend.dtos;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private String dateCreated;
    private List<OrderProductDTO> orderProducts;
    private PromoCodeDTO promoCode;
    private BigDecimal totalPrice;
    private String address;
    private String contactPhoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public PromoCodeDTO getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeDTO promoCode) {
        this.promoCode = promoCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
}
