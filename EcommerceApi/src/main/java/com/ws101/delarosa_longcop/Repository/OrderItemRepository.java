package com.ws101.delarosa_longcop.repository;



import com.ws101.delarosa_longcop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemrepository extends JpaRepository<OrderItem, Long> {
}