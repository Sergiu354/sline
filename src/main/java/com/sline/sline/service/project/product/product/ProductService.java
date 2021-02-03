package com.sline.sline.service.project.product.product;

import com.sline.sline.entity.project.protuct.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void save(Product product);
    Product findById(Long id);
    Product findByUuid(String uuid, Long companyId);
    void deleteById(Long id);
    void deleteByUuid(String uuid);
    Page<Product> findAllByInStock(Integer stockSize, Long companyId,  Pageable pageable);
    Page<Product> findAll(Long companyId, Pageable pageable);
}
