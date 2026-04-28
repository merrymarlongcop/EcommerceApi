package com.ws101.delarosa_longcop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a product in the e-commerce system.
 * Each product belongs to one category, while one category can have many products.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Unique identifier for each product - primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product, cannot be empty and must be at least 2 characters
     */
    @NotBlank(message = "Product name is required and cannot be empty")
    @Size(min = 2, message = "Product name must be at least 2 characters long")
    @Column(name = "product_name", nullable = false)
    private String productName;

    /**
     * Description of the product
     */
    private String description;

    /**
     * Price of the product, cannot be null and must be a positive value
     */
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    @Column(nullable = false)
    private Double price;

    /**
     * Name of the category this product belongs to
     */
    @NotBlank(message = "Category is required and cannot be empty")
    @Column(nullable = false)
    private String category;

    /**
     * Available stock quantity, cannot be null and cannot be negative
     */
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    @Column(nullable = false)
    private Integer stockQuantity;

    /**
     * URL link to the product image
     */
    private String imageUrl;

    /**
     * Many‑to‑One relationship: Many products belong to one Category entity.
     * Uses FetchType.LAZY (loads data only when needed) and CascadeType.ALL
     * (any operation on product will also affect related category data).
     * JsonIgnore prevents infinite loops when converting data to JSON format.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id") // Creates foreign key linking to categories table
    @JsonIgnore
    private Category categoryEntity;
}