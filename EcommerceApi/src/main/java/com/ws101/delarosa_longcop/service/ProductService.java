package com.ws101.delarosa_longcop.service;

import com.ws101.delarosa_longcop.model.Product;
import com.ws101.delarosa_longcop.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service

public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // Constructor with initial sample data when the application starts
    public ProductService() {
        products.add(new Product(nextId.getAndIncrement(), "Laptop", "High performance laptop", 45000.0, "Electronics", 10, "url1"));
        products.add(new Product(nextId.getAndIncrement(), "Smartphone", "Latest model smartphone", 22000.0, "Electronics", 15, "url2"));
        products.add(new Product(nextId.getAndIncrement(), "Shirt", "Cotton casual shirt", 550.0, "Apparel", 30, "url3"));
        products.add(new Product(nextId.getAndIncrement(), "Shoes", "Comfortable running shoes", 1800.0, "Apparel", 20, "url4"));
        products.add(new Product(nextId.getAndIncrement(), "Blender", "High speed kitchen blender", 3200.0, "Home Appliances", 8, "url5"));
    }

    // Retrieve all existing products
    public List<Product> getAllProducts() {
        return products;
    }

    // Retrieve a single product by its ID
    // Throws an error if the product is not found
    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " was not found"));
    }

    // Retrieve products that match the given category
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // Add and save a new product
    public Product createProduct(Product product) {
        product.setId(nextId.getAndIncrement());
        products.add(product);
        return product;
    }

    // Update all details of an existing product
    // Throws an error if the product does not exist
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

    // Delete a product from the list
    // Throws an error if the product does not exist
    public boolean deleteProduct(Long id) {
        Product productToDelete = getProductById(id);
        products.remove(productToDelete);
        return true;
    }

}