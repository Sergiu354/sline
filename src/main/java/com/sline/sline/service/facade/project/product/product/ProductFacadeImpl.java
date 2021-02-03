package com.sline.sline.service.facade.project.product.product;

import com.sline.sline.dto.company.CompanyDto;
import com.sline.sline.dto.product.AmountDto;
import com.sline.sline.dto.product.MediaDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.entity.project.company.Company;
import com.sline.sline.entity.project.protuct.*;
import com.sline.sline.service.facade.project.product.type.TypeFacade;
import com.sline.sline.service.project.company.CompanyService;
import com.sline.sline.service.project.product.product.ProductService;
import com.sline.sline.session.DataSession;
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

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Service
@Transactional
@AllArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ProductService productService;
    private final CompanyService companyService;
    private final TypeFacade typeFacade;
    private final DataSession dataSession;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void setProperty() {
        addMappingsDto(modelMapper);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Company company = companyService.findById(dataSession.getCompanyId());
        Product product = new Product();
        convertDtoToEntity(productDto, product);
        product.getTypes().forEach(typeFacade::setUuid);
        product.getTypes().forEach(type -> type.setProduct(product));
        product.setCompany(company);
        productService.save(product);
        return convertEntityToDto(product);
    }


    @Override
    public ProductDto findByUuid(String uuid) {
        Product product = productService.findByUuid(uuid, dataSession.getCompanyId());
        return convertEntityToDto(product);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(dataSession.getCompanyId(), pageable).map(this::convertEntityToDto);
    }

    @Override
    public ProductDto edit(ProductDto productDto) {
        if (productDto.getUuid()==null) {
            LOGGER.info("ProductDto uuid is null");
            return null;
        }

        Product product = productService.findByUuid(productDto.getUuid(), dataSession.getCompanyId());

        if (product==null) {
            LOGGER.info("Product is null find by uuid: " + productDto.getUuid());
            return null;
        }

        productDto.setTypes(new HashSet<>());
        convertDtoToEntity(productDto, product);
        product.getTypes().remove(product.getTypes().iterator().next());
        productService.save(product);

        return convertEntityToDto(product);
    }

    @Override
    public Page<ProductDto> findAllByInStock(Integer stockSize, Pageable pageable) {
        return productService.findAllByInStock(stockSize, dataSession.getCompanyId(), pageable).map(this::convertEntityToDto);
    }

    @Override
    public void deleteByUuid(String uuid) {
        productService.deleteByUuid(uuid);
    }

    private ProductDto convertEntityToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private void convertDtoToEntity(ProductDto productDto, Product product) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        /*modelMapper.addMappings(new PropertyMap<ProductDto, Product> (){
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
                skip(destination.getCompany());
                if (isEdit)
                    skip(destination.getTypes());
            }
        });*/
        /*if (product.getId()!=null)
            modelMapper.typeMap(ProductDto.class, Product.class).addMappings(mapping -> mapping.skip((destination, value) -> destination.getTypes()));*/
        modelMapper.map(productDto, product);
    }

    private void addMappingsDto(ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<Company, CompanyDto>() {
            @Override
            protected void configure() {
                skip(destination.getProducts());
                skip(destination.getOrders());
                skip(destination.getUser());
            }
        });

        modelMapper.addMappings(new PropertyMap<Type, TypeDto>() {
            @Override
            protected void configure() {
                skip(destination.getProduct());
            }
        });

        modelMapper.addMappings(new PropertyMap<Media, MediaDto>() {
            @Override
            protected void configure() {
                skip(destination.getType());
            }
        });

        modelMapper.addMappings(new PropertyMap<Amount, AmountDto>() {
            @Override
            protected void configure() {
                skip(destination.getType());
            }
        });

        modelMapper.addMappings(new PropertyMap<ProductDto, Product>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
                skip(destination.getCompany());
            }
        });
    }

}
