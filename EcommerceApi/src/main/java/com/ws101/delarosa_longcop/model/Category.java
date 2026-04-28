package com.ws101.delarosa_longcop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Entity class representing a product category.
 * One category can contain many products.
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * Unique identifier for each category - primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category
     */
    @Column(nullable = false)
    private String name;

    /**
     * One‑to‑Many relationship: One category has many products.
     * FetchType.LAZY and CascadeType.ALL applied as required.
     * MappedBy refers to the field in Product that owns the relationship.
     */
    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}