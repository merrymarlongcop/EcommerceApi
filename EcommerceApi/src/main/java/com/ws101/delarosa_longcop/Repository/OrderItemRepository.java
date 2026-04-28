package com.ws101.delarosa_longcop.repository;

import com.ws101.delarosa_longcop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Category entity.
 * Provides built-in database operations.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
package com.ws101.delarosa_longcop.repository;

import com.ws101.delarosa_longcop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for OrderItem entity.
 * Provides built-in database operations.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}