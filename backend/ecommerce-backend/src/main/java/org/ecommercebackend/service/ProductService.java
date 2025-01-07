package org.ecommercebackend.service;

import org.ecommercebackend.dtos.ProductDTO;
import org.ecommercebackend.dtos.ProductMapper;
import org.ecommercebackend.exceptions.ProductNotFoundException;
import org.ecommercebackend.models.Product;
import org.ecommercebackend.repositories.ProductRepository;
import org.ecommercebackend.requests.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        List<ProductDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(ProductMapper.toProductDTO(product));
        }

        return dtos;
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
