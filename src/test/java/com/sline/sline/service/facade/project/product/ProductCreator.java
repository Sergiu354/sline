package com.sline.sline.service.facade.project.product;

import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.service.facade.project.Generator;
import com.sline.sline.service.facade.project.product.type.TypeCreator;

import java.util.UUID;

public abstract class ProductCreator extends Generator {
    public static ProductDto createProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setUuid(UUID.randomUUID().toString());
        productDto.setName(getRandomString(20));
        productDto.setPrice((float) getRandomNumber(100, 5000));
        productDto.setDiscount((float) getRandomNumber(0, 50));
        productDto.setDescription(getRandomString(200));
        productDto.setTypes(TypeCreator.createType(productDto, 10));
        return productDto;
    }
}
