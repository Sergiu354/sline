package com.sline.sline.service.facade.project.product;

import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.service.facade.project.product.product.ProductFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class ProductFacadeTest {
    @Autowired
    private ProductFacade productFacade;
    private String uuid = "23bb6377-e88d-466e-bba3-4c7175ec378d";

    @Test
    void save() {
        for (int i = 0; i<100; i++) {
            ProductDto productDto = ProductCreator.createProduct();
            productFacade.save(productDto);
        }
    }

    @Test
    void findByUuid() {
        ProductDto productDto = productFacade.findByUuid(uuid);
        System.out.println(productDto);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0,18);
        Page<ProductDto> productDtos = productFacade.findAll(pageable);
        System.out.println(productDtos.getTotalElements());
        //System.out.println(productDtos);
    }

    @Test
    void edit() {
        ProductDto productDto = productFacade.findByUuid(uuid);
        productDto.setName(productDto.getName() + " - Edit");
        productFacade.edit(productDto);
        ProductDto productDto1 = productFacade.findByUuid(uuid);
        System.out.println(productDto1);

    }

    @Test
    void findAllByInStock() {
        Pageable pageable = PageRequest.of(0,18, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductDto> productDtos = productFacade.findAllByInStock(10, pageable);
        //Page<ProductDto> productDtosZero = productFacade.findAllByInStock(0, pageable);
        productDtos.stream().forEach(System.out::println);
        //System.out.println(productDtosZero);
    }

    @Test
    void deleteByUuid() {
        productFacade.deleteByUuid("5752c534-4f61-40ca-9a45-ff6028bd0a62");
    }
}
