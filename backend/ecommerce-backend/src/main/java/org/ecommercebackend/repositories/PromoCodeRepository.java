package org.ecommercebackend.repositories;

import org.ecommercebackend.models.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    @Query(value = "SELECT * FROM promo_code WHERE code=:code", nativeQuery = true)
    Optional<PromoCode> findByCode(String code);
}
