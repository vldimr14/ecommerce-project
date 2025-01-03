package org.ecommercebackend.service;

import org.ecommercebackend.models.Product;
import org.ecommercebackend.repositories.ProductRepository;
import org.ecommercebackend.requests.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setColor(productRequest.getColor());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setSize(productRequest.getSize());
        product.setImageUrl(productRequest.getImageUrl());

        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findProductById(id);

        product.setName(productRequest.getName());
        product.setSize(productRequest.getSize());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setColor(productRequest.getColor());
        product.setImageUrl(productRequest.getImageUrl());

        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
}
