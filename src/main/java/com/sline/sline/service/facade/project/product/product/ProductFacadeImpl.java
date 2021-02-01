package com.sline.sline.service.facade.project.product.product;

import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.entity.project.protuct.*;
import com.sline.sline.service.facade.project.product.type.TypeFacade;
import com.sline.sline.service.project.product.product.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ProductService productService;
    private final TypeFacade typeFacade;


    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = new Product();

        convertDtoToEntity(productDto, product, false);

        //product.setUuid(UUID.randomUUID().toString());
        product.getTypes().forEach(typeFacade::setUuid);
        product.getTypes().forEach(type -> type.setProduct(product));

        productService.save(product);

        return convertEntityToDto(product);
    }

    @Override
    public ProductDto findByUuid(String uuid) {
        Product product = productService.findByUuid(uuid);
        return convertEntityToDto(product);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(pageable).map(this::convertEntityToDto);
    }

    @Override
    public ProductDto edit(ProductDto productDto) {
        if (productDto.getUuid()==null) {
            LOGGER.info("ProductDto uuid is null");
            return null;
        }

        Product product = productService.findByUuid(productDto.getUuid());

        if (product==null) {
            LOGGER.info("Product is null find by uuid: " + productDto.getUuid());
            return null;
        }

        convertDtoToEntity(productDto, product, true);
        product.getTypes().remove(product.getTypes().iterator().next());
        productService.save(product);

        return convertEntityToDto(product);
    }

    @Override
    public Page<ProductDto> findAllByInStock(Integer stockSize, Pageable pageable) {
        return productService.findAllByInStock(stockSize, pageable).map(this::convertEntityToDto);
    }

    @Override
    public void deleteByUuid(String uuid) {
        productService.deleteByUuid(uuid);
    }

    private ProductDto convertEntityToDto(Product product) {
        if (product==null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDto.class);
    }

    private void convertDtoToEntity(ProductDto productDto, Product product, boolean isEdit) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.addMappings(new PropertyMap<ProductDto, Product> (){
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
                if (isEdit)
                    skip(destination.getTypes());
            }
        });

        modelMapper.map(productDto, product);
    }
}
