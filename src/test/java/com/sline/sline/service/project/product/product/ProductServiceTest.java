package com.sline.sline.service.project.product.product;

import com.sline.sline.entity.project.protuct.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void findAllByInStock() {
        Pageable pageable = PageRequest.of(0,18, Sort.by(Sort.Direction.DESC, "id"));
        Page<Product> products = productService.findAllByInStock(29, (long) 1, pageable);
    }
}
