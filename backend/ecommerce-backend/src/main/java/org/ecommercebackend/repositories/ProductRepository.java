package org.ecommercebackend.repositories;

import org.ecommercebackend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product save(Product product);

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE product_id = :id", nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = "SELECT * FROM product WHERE product_name = :name", nativeQuery = true)
    Optional<Product> findProductByName(String name);

    @Modifying
    @Query(value = "DELETE FROM product WHERE product_id = :id", nativeQuery = true)
    void deleteProductById(Long id);
}
