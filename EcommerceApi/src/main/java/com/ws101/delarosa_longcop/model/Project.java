package com.ws101.delarosa_longcop;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private String productName;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;
    private String imageUrl;
}