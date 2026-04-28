package com.ws101.delarosa_longcop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity class representing a customer order.
 * One order can have many order items.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Unique identifier for each order - primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date and time when the order was placed
     */
    private LocalDateTime orderDate;

    /**
     * Total amount of the order
     */
    private double totalAmount;

    /**
     * Status of the order
     */
    private String status;

    /**
     * One‑to‑Many relationship: One order contains many order items.
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}