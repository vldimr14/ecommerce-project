package org.ecommercebackend.controllers;

import org.ecommercebackend.dtos.ProductDTO;
import org.ecommercebackend.requests.ProductRequest;
import org.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(name = "sortBy", required = false) String sortBy,
                                                           @RequestParam(name = "name", required = false) String name,
                                                           @RequestParam(name = "category", required = false) String category) {
        Integer size = 10;
        Pageable pageable;

        if (sortBy != null) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<ProductDTO> pageProduct = productService.getAllProducts(pageable, name, category);
        return new ResponseEntity<>(pageProduct.getContent(), HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDTO>> getSearchedProducts(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(name = "sortBy", required = false) String sortBy,
                                                           @RequestParam(name = "search", required = false) String search
                                                           ) {
        Integer size = 10;
        Pageable pageable;

        if (sortBy != null) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<ProductDTO> pageProduct = productService.getSearchedProducts(pageable, search);
        return new ResponseEntity<>(pageProduct.getContent(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product has been successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
