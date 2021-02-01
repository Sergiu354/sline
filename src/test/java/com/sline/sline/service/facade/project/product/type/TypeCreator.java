package com.sline.sline.service.facade.project.product.type;

import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.service.facade.project.Generator;
import com.sline.sline.service.facade.project.product.amount.AmountCreator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class TypeCreator extends Generator {

    public static Set<TypeDto> createType(ProductDto productDto, int limit) {
        Set<TypeDto> types = new HashSet<>();
        for (int i = 0; i<limit; i++) {
            TypeDto type = new TypeDto();
            type.setUuid(UUID.randomUUID().toString());
            type.setName(getRandomString(20));
            type.setProduct(productDto);
            type.setAmount(AmountCreator.createAmount(type));
            //type.setImages();
            //type.setVideos();
            types.add(type);
        }
        return types;
    }
}
