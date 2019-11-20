package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Adeel.Asghar
 */

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private int quantity;
    private int price;
}