package org.ecommerceshop.ecommercebackend.repositories;

import org.ecommerceshop.ecommercebackend.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE id = ?", nativeQuery = true)
    Product findProductById(Long id);

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllProducts();

    @Query("UPDATE product SET name = ?, description = ?, category = ?, color = ?, price = ?, size = ?, imageUrl = ? WHERE id = ?")
    void updateProduct(Product product);

    @Query("DELETE FROM product WHERE id = ?")
    void deleteProductById(Long id);
}
