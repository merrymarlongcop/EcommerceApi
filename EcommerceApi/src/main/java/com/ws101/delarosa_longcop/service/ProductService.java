package com.ws101.delarosa_longcop.service;

import com.ws101.delarosa_longcop.model.Product;
import com.ws101.delarosa_longcop.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for product-related operations.
 * <p>
 * Provides business logic for creating, retrieving, updating, deleting, 
 * and filtering product data. This class acts as an intermediary between 
 * the API controller and the data storage layer, handling all core operations 
 * related to product management.
 * </p>
 * 
 * @author delarosa_longcop
 * @see Product
 * @see ResourceNotFoundException
 */
@Service

public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    /**
     * Constructor that initializes the service with sample product data.
     * <p>
     * This is executed automatically when the application starts, providing 
     * initial records for testing and demonstration purposes.
     * </p>
     */
    public ProductService() {
        products.add(new Product(nextId.getAndIncrement(), "Laptop", "High performance laptop", 45000.0, "Electronics", 10, "url1"));
        products.add(new Product(nextId.getAndIncrement(), "Smartphone", "Latest model smartphone", 22000.0, "Electronics", 15, "url2"));
        products.add(new Product(nextId.getAndIncrement(), "Shirt", "Cotton casual shirt", 550.0, "Apparel", 30, "url3"));
        products.add(new Product(nextId.getAndIncrement(), "Shoes", "Comfortable running shoes", 1800.0, "Apparel", 20, "url4"));
        products.add(new Product(nextId.getAndIncrement(), "Blender", "High speed kitchen blender", 3200.0, "Home Appliances", 8, "url5"));
    }

    /**
     * Retrieves all products currently stored in the system.
     * <p>
     * This method returns the complete list of available products. 
     * If there are no products, it will return an empty list.
     * </p>
     * 
     * @return List<Product> A list containing all product objects.
     *         Returns an empty list if no products exist.
     * 
     * @example
     * <pre>{@code
     * List<Product> allProducts = productService.getAllProducts();
     * }</pre>
     */
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * Retrieves a single product using its unique identifier.
     * <p>
     * Searches the stored data for a product that matches the provided ID.
     * Throws an exception if no product with the given ID is found.
     * </p>
     * 
     * @param id The unique numeric identifier of the product to retrieve.
     * @return Product The product object containing all details of the requested item.
     * @throws ResourceNotFoundException If no product with the specified ID exists in the system.
     * 
     * @example
     * <pre>{@code
     * Product product = productService.getProductById(3L);
     * }</pre>
     */
    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " was not found"));
    }

    /**
     * Retrieves products that belong to a specific category.
     * <p>
     * Filters the list of products and returns only those whose category 
     * matches the given value, ignoring differences in uppercase or lowercase letters.
     * </p>
     * 
     * @param category The category name used to filter the products.
     * @return List<Product> A list of products that fall under the specified category.
     *         Returns an empty list if no products match the given category.
     * 
     * @example
     * <pre>{@code
     * List<Product> electronics = productService.getProductsByCategory("Electronics");
     * }</pre>
     */
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    /**
     * Creates and stores a new product record.
     * <p>
     * Assigns a unique ID to the new product and saves it into the storage list.
     * The generated ID is automatically incremented to ensure uniqueness.
     * </p>
     * 
     * @param product The product object containing the details to be saved.
     * @return Product The newly created product object, including its generated unique ID.
     * 
     * @example
     * <pre>{@code
     * Product newProduct = new Product();
     * newProduct.setProductName("Wireless Mouse");
     * newProduct.setPrice(750.0);
     * newProduct.setCategory("Electronics");
     * Product savedProduct = productService.createProduct(newProduct);
     * }</pre>
     */
    public Product createProduct(Product product) {
        product.setId(nextId.getAndIncrement());
        products.add(product);
        return product;
    }

    /**
     * Updates the details of an existing product.
     * <p>
     * Replaces all existing details of the product with the new data provided.
     * If the product ID does not exist, an exception will be thrown.
     * </p>
     * 
     * @param id The unique identifier of the product to update.
     * @param updatedProduct The product object containing the new details to apply.
     * @return Product The updated product object reflecting all new changes.
     * @throws ResourceNotFoundException If no product with the specified ID exists.
     * 
     * @example
     * <pre>{@code
     * Product updatedDetails = new Product();
     * updatedDetails.setProductName("Updated Laptop");
     * updatedDetails.setPrice(42000.0);
     * Product updatedProduct = productService.updateProduct(1L, updatedDetails);
     * }</pre>
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        return existingProduct;
    }

    /**
     * Deletes a product record from the system.
     * <p>
     * Removes the specified product from the storage list.
     * Throws an exception if the product with the given ID is not found.
     * </p>
     * 
     * @param id The unique identifier of the product to delete.
     * @return boolean Returns {@code true} if the product was successfully deleted.
     * @throws ResourceNotFoundException If no product with the specified ID exists.
     * 
     * @example
     * <pre>{@code
     * boolean isDeleted = productService.deleteProduct(5L);
     * }</pre>
     */
    public boolean deleteProduct(Long id) {
        Product productToDelete = getProductById(id);
        products.remove(productToDelete);
        return true;
    }

}
