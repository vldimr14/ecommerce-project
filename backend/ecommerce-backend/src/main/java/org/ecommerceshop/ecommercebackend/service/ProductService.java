package org.ecommerceshop.ecommercebackend.service;

import org.ecommerceshop.ecommercebackend.models.Product;
import org.ecommerceshop.ecommercebackend.repositories.ProductRepository;
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

    public Product getProduct(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
    }
}
