package com.demo.entity;

/**
 * Created by Adeel.Asghar on 11/19/2019.
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String state;
    private String zipcode;
}