package org.ecommercebackend.dtos;

import org.ecommercebackend.models.Product;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setColor(product.getColor());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setSize(product.getSize());
        dto.setPrice(product.getPrice());

        return dto;
    }
}
