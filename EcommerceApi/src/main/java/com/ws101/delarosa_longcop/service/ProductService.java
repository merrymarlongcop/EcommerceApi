package com.ws101.delarosa_longcop.service;
import com.ws101.delarosa_longcop.model.product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing products in memory.
 * Uses a List<Product> to store data temporarily without a database.
 * ID Generation: Uses a simple counter to ensure unique IDs.
 */


@Service 
public class ProductService {
    // List to store all product data in memory
    private List<Product> products = new ArrayList<>();

    //COUNTER FOR ID GENERAION
    private Long nextId = 1L;

    // Constructor - Add initial sample data (at least 10 products)
    public ProductService() {
        products.add(new Product(1L, "Laptop", "Gaming Laptop", 50000.00, "Electronics", 10, "url1"));
        products.add(new Product(2L, "Mouse", "Wireless Mouse", 800.00, "Accessories", 50, "url2"));
        products.add(new Product(3L, "Keyboard", "Mechanical Keyboard", 2500.00, "Accessories", 30, "url3"));
        products.add(new Product(4L, "Monitor", "LED Monitor", 12000.00, "Electronics", 15, "url4"));
        products.add(new Product(5L, "Headphones", "Noise Cancelling", 3500.00, "Audio", 20, "url5"));
        products.add(new Product(6L, "Phone", "Smartphone", 25000.00, "Electronics", 25, "url6"));
        products.add(new Product(7L, "Charger", "Fast Charger", 500.00, "Accessories", 100, "url7"));
        products.add(new Product(8L, "Webcam", "HD Webcam", 1500.00, "Electronics", 12, "url8"));
        products.add(new Product(9L, "Speaker", "Bluetooth Speaker", 1200.00, "Audio", 18, "url9"));
        products.add(new Product(10L, "Tablet", "Android Tablet", 18000.00, "Electronics", 8, "url10"));
    }

    // 1. Get all products from the list
    public List<Product> getAllProducts() {
        return products;
    }

    // 2. Find a specific product by its ID
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // 3. Add a new product to the list
    public Product createProduct(Product product) {
       // Assign the next available ID automatically
       product.setld(nextId++);
       products.add(product);
       return product;
    }

    // 4. Update details of an existing product
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> optionalProduct = getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productDetails.getProductName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            product.setStockQuantity(productDetails.getStockQuantity());
            product.setImageUrl(productDetails.getImageUrl());
            return product;
        }
        return null;
    }

    // 5. Remove a product from the list
    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    // 6. Filter and get products based on category
    public List<Product> getProductsByCategory(String category) {
        return products.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).toList();
    }
}
