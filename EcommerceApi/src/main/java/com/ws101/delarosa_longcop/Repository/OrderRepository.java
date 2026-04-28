package com.ws101.delarosa_longcop.repository;

import com.ws101.delarosa_longcop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Order entity.
 * Provides built-in database operations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}