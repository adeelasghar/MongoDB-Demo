package com.demo.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.entity.Address;
import com.demo.entity.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "order_DB")
public class User {

	@Id
	private int id;

	private String name;

	private String gender;

	private List<Product> products;

	private Address address;

	private Map<String, String> userSettings = new HashMap<>();

}