package com.sline.sline.service.facade.project.product;

import com.sline.sline.dto.company.CompanyDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.service.facade.project.company.CompanyFacade;
import com.sline.sline.service.facade.project.product.product.ProductFacade;
import com.sline.sline.session.DataSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;

@SpringBootTest
class ProductFacadeTest {
    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private CompanyFacade companyFacade;

    private String uuid = "4a402002-bbc3-453a-8a75-7e6f02bbfc9d";
    private Pageable pageable = PageRequest.of(0,18, Sort.by(Sort.Direction.DESC, "id"));

    @Autowired
    private DataSession dataSession;

    @PostConstruct
    void start() {
        dataSession.setCompanyId((long) 1);
    }

    @Test
    void save() { // 8min
        for (int i = 0; i<5000; i++) {
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
        Page<ProductDto> productDtos = productFacade.findAll(pageable);
        System.out.println(productDtos.getTotalElements());
        //System.out.println(productDtos);
    }

    @Test
    void edit() {
        ProductDto productDto = productFacade.findAll(pageable).iterator().next();
        productDto.setName(productDto.getName() + " - Edit");
        productFacade.edit(productDto);
    }

    @Test
    void findAllByInStock() {
        dataSession.setCompanyId((long) 1);
        Page<ProductDto> productDtos = productFacade.findAllByInStock(10, pageable);
        System.out.println(productDtos);
        //productDtos.stream().forEach(System.out::println);
    }

    @Test
    void deleteByUuid() {
        productFacade.deleteByUuid("5752c534-4f61-40ca-9a45-ff6028bd0a62");
    }
}
