package org.ecommercebackend.requests;

import java.math.BigDecimal;

public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private String color;
    private BigDecimal price;
    private String size;
    private String imageUrl;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, String category, String color, BigDecimal price, String size, String imageUrl) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.color = color;
        this.price = price;
        this.size = size;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
