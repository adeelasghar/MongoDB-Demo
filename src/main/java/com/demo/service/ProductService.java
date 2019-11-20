package com.demo.service;

import com.demo.dto.ResponseDTO;

/**
 * @author Adeel.Asghar
 */

public interface ProductService {
    ResponseDTO getByProductId(String productId);
}
