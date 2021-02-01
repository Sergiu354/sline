package com.sline.sline.service.facade.project.product.product;

import com.sline.sline.dto.product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductFacade {
    ProductDto save(ProductDto productDto);
    ProductDto findByUuid(String uuid);
    Page<ProductDto> findAll(Pageable pageable);
    ProductDto edit(ProductDto productDto);
    Page<ProductDto> findAllByInStock(Integer stockSize, Pageable pageable);
    void deleteByUuid(String uuid);
}
