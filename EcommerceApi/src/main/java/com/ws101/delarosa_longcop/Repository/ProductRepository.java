package com.ws101.delarosa_longcop.repository;

import com.ws101.delarosa_longcop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository interface for Product entity.
 * Provides built-in CRUD operations and custom query methods.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finder method created using Spring Data JPA Method Naming convention.
     * Finds all products that belong to a specific category name.
     * 
     * @param categoryName the name of the category to filter products by
     * @return list of products matching the given category name
     */
    List<Product> findByCategory(String categoryName);

    /**
     * Custom query using JPQL to find products within a specific price range.
     * Uses @Query annotation to define the query manually.
     * 
     * @param minPrice the minimum price limit
     * @param maxPrice the maximum price limit
     * @return list of products whose price is between the given values
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductsByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
}