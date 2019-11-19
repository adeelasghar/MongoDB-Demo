package com.demo.entity;

/**
 * Created by Adeel.Asghar on 11/19/2019.
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private int quantity;
    private int price;
}