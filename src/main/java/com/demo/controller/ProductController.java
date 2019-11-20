package com.demo.controller;

import com.demo.dto.ResponseDTO;
import com.demo.entity.User;
import com.demo.service.ProductService;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Adeel.Asghar
 */

@RestController
@RequestMapping(value = "/product/")
public class ProductController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseDTO addNewUsers(@PathVariable String productId) {
		return  productService.getByProductId(productId);
	}

}