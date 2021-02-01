package com.sline.sline.controller.product;

import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.model.CurrentProfile;
import com.sline.sline.service.facade.project.product.product.ProductFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController()
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ProductFacade productFacade;

    @GetMapping
    public Page<ProductDto> findAll(@PageableDefault(size=18) @SortDefault(sort="id") Pageable pageable, Principal principal) {
        System.out.println(principal.getName());
        return productFacade.findAll(pageable);
    }

    @GetMapping(value = "/stock/{size}")
    public Page<ProductDto> findStock(@PathVariable Integer size, @PageableDefault(size = 18) @SortDefault(sort = "id") Pageable pageable) {
        return productFacade.findAllByInStock(size, pageable);
    }

    @GetMapping(value = "/{uuid}")
    public ProductDto findByUuid(@PathVariable String uuid) {
        return productFacade.findByUuid(uuid);
    }

    @PostMapping(value = "/save")
    public ProductDto saveNewProduct(@ModelAttribute ProductDto productDto) {
        return productFacade.save(productDto);
    }

    @PostMapping(value = "/save-edit")
    public void saveEditProduct(@ModelAttribute ProductDto productDto) {
        if (productDto.getUuid()!=null) {
            productFacade.edit(productDto);
        } else
            LOGGER.info("Product uuid is null");
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        productFacade.deleteByUuid(uuid);
    }
}
