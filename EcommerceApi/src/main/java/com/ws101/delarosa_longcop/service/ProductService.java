package com.ws101.delarosa_longcop.service;


import com.ws101.delarosa_longcop.exception.ResourceNotFoundException;
import com.ws101.delarosa_longcop.model.Product;
import com.ws101.delarosa_longcop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * Service class for handling business logic related to products.
 * Uses Spring Data JPA Repository instead of manual list operations.
 */
@Service

public class ProductService {

    // Inject the repository - replaces the old ArrayList storage
    private final ProductRepository productRepository;

    /**
     * Constructor injection of ProductRepository
     * 
     * @param productRepository the repository instance to be used
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieve all products from the database
     * 
     * @return list of all available products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get a single product by its ID
     * 
     * @param id the unique identifier of the product
     * @return the product if found
     * @throws ResourceNotFoundException if no product exists with the given ID
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    /**
     * Create and save a new product to the database
     * 
     * @param product the product data to be saved
     * @return the saved product with generated ID
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update an existing product's details
     * 
     * @param id the ID of the product to update
     * @param updatedProduct the new data for the product
     * @return the updated product
     * @throws ResourceNotFoundException if the product does not exist
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(existingProduct);
    }

    /**
     * Delete a product from the database
     * 
     * @param id the ID of the product to delete
     */
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    /**
     * Find products by category name - uses the custom finder method from repository
     * 
     * @param categoryName the name of the category to filter by
     * @return list of products in the specified category
     */
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }

    /**
     * Find products within a given price range - uses the custom JPQL query
     * 
     * @param minPrice minimum price value
     * @param maxPrice maximum price value
     * @return list of products whose price falls between the given range
     */
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findProductsByPriceRange(minPrice, maxPrice);
    }
}