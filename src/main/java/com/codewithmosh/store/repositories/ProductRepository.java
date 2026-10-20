package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Strings
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameNotContaining(String name);
    List<Product> findByNameNotStartingWith(String name);
    List<Product> findByNameNotEndingWith(String name);
    List<Product> findByNameNotEndingWithIgnoreCase(String name);

    // Numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    // Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    // Sort (OrderBy)
    List<Product> findByNameOrderByPrice(String name);
    List<Product> findByNameOrderByPriceDesc(String name);

    // Limit (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    // Find products whose prices are in a given range and sort by name
    // SQL or JPQL query
    @Query("SELECT p FROM Product p JOIN p.category WHERE p.price BETWEEN :min AND :max ORDER BY p.name")
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

}
