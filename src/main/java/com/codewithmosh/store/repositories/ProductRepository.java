package com.codewithmosh.store.repositories;

import com.codewithmosh.store.dtos.ProductSummary;
import com.codewithmosh.store.dtos.ProductSummaryDTO;
import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Strings
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameNotLikeIgnoreCase(String name);
    List<Product> findByNameNotContaining(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

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
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("SELECT COUNT(*) FROM Product p WHERE p.price BETWEEN :min AND :max")
    Long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("UPDATE Product p SET p.price = :newPrice WHERE p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    @Query("SELECT new com.codewithmosh.store.dtos.ProductSummaryDTO(p.id, p.name) FROM Product p WHERE p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}
