package org.ecommercebackend.repositories;

import org.ecommercebackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    @Query(value = "SELECT * FROM product WHERE product_id = :id", nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllProducts();

    @Modifying
    @Query(value = "DELETE FROM product WHERE product_id = :id", nativeQuery = true)
    void deleteProductById(Long id);
}
