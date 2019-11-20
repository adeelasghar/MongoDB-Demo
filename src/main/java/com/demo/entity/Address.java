package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Adeel.Asghar
 */

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String state;
    private String zipcode;
}