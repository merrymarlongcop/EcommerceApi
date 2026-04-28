package com.ws101.delarosa_longcop.controller;

import com.ws101.delarosa_longcop.model.Product;
import com.ws101.delarosa_longcop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * REST Controller for handling HTTP requests related to products.
 * Provides endpoints for creating, retrieving, updating and deleting products.
 */
@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    /**
     * Inject the ProductService dependency
     * @param productService service instance containing business logic
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET all products
     * @return list of all products stored in the database
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * GET a single product by ID
     * @param id the unique identifier of the product
     * @return the requested product details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * POST - Create a new product
     * @param product product data sent in the request body
     * @return the created product with generated ID
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * PUT - Update an existing product
     * @param id ID of the product to update
     * @param product updated product data
     * @return the updated product details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * DELETE - Remove a product
     * @param id ID of the product to delete
     * @return response with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * GET products by category name
     * @param categoryName name of the category to filter by
     * @return list of products in the specified category
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryName));
    }

    /**
     * GET products within a price range
     * @param minPrice minimum price value
     * @param maxPrice maximum price value
     * @return list of products whose price falls between the given values
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }
}