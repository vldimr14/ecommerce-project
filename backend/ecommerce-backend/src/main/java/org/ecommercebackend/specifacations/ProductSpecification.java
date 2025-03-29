package org.ecommercebackend.specifacations;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.ecommercebackend.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> filterProduct(String name, String category) {
        return (root, query, criteriaBuilder) -> {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), StringUtils.isBlank(name)
                    ? likePattern("") : name);
            Predicate categoryPredicate = criteriaBuilder.like(root.get("category"), StringUtils.isBlank(category)
                    ? likePattern("") : category);

            return criteriaBuilder.and(namePredicate, categoryPredicate);
        };
    }

    public static Specification<Product> searchProduct(String search) {
        return (root, query, criteriaBuilder) -> {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), likePattern(search));
            Predicate categoryPredicate = criteriaBuilder.like(root.get("category"), likePattern(search));

            return criteriaBuilder.or(namePredicate, categoryPredicate);
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
