package org.ecommercebackend.service;

import org.ecommercebackend.dtos.ProductDTO;
import org.ecommercebackend.dtos.ProductMapper;
import org.ecommercebackend.exceptions.ProductNotFoundException;
import org.ecommercebackend.models.Product;
import org.ecommercebackend.repositories.ProductRepository;
import org.ecommercebackend.requests.ProductRequest;
import org.ecommercebackend.specifacations.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO saveProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setColor(productRequest.getColor());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setSize(productRequest.getSize());
        product.setImageUrl(productRequest.getImageUrl());

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toProductDTO(savedProduct);
    }

    public ProductDTO getProduct(Long id) {
        Product product =  productRepository.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        return ProductMapper.toProductDTO(product);
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable, String name, String category) {
        final Specification<Product> specification =
                ProductSpecification.filterProduct(name, category);

        List<Product> products = productRepository.findAll(specification, pageable).toList();
        List<ProductDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(ProductMapper.toProductDTO(product));
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }

    public Page<ProductDTO> getSearchedProducts(Pageable pageable, String search) {
        final Specification<Product> specification =
                ProductSpecification.searchProduct(search);

        List<Product> products = productRepository.findAll(specification, pageable).toList();
        List<ProductDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(ProductMapper.toProductDTO(product));
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }

    public ProductDTO updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));

        product.setName(productRequest.getName());
        product.setSize(productRequest.getSize());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setDescription(productRequest.getDescription());
        product.setColor(productRequest.getColor());
        product.setImageUrl(productRequest.getImageUrl());

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toProductDTO(updatedProduct);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
}
