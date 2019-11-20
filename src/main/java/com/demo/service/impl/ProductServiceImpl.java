package com.demo.service.impl;

import com.demo.dto.ResponseDTO;
import com.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adeel.Asghar
 */

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Override
    public ResponseDTO getByProductId(String productId) {
        return null;
    }
}
