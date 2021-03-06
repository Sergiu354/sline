package com.sline.sline.service.facade.project.product.type;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.service.facade.project.product.amount.AmountFacade;
import com.sline.sline.service.facade.project.product.media.MediaFacade;
import com.sline.sline.service.project.product.type.TypeService;
import com.sline.sline.service.project.product.product.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeFacadeImpl implements TypeFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final ProductService productService;
    private final TypeService typeService;
    private final AmountFacade amountFacade;
    private final MediaFacade mediaFacade;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void construct() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.addMappings(new PropertyMap<TypeDto, Type>(){
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
            }
        });
    }

    private static Long userId = (long) 1;

    @Override
    public TypeDto findByUuid(String uuid) {
        return convertEntityToDto(typeService.findByUuid(uuid));
    }

    @Override
    public List<TypeDto> findAllByByProduct(ProductDto productDto) {
        return findAllByByProductUuid(productDto.getUuid());
    }

    @Override
    public List<TypeDto> findAllByByProductUuid(String productUuid) {
        return typeService.findByProductUuid(productUuid).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void save(TypeDto typeDto) {
        save(typeDto.getProduct().getUuid(), typeDto);
    }

    @Override
    public void save(String productUuid, TypeDto typeDto) {
        Product product = productService.findByUuid(productUuid, userId);
        if (product!=null) {
            Type type = new Type();
            convertDtoToEntity(typeDto, type);
            type.setProduct(product);
            typeService.save(type);
            typeDto = convertEntityToDto(type);
        } else
            LOGGER.info("Product not found UUID: " + productUuid);
    }

    @Override
    public void save(String productUuid, List<TypeDto> typeDtos) {
        Product product = productService.findByUuid(productUuid, userId);
        Set<Type> types = new HashSet<>();
        for (TypeDto typeDto : typeDtos) {
            Type type = new Type();
            convertDtoToEntity(typeDto, type);
            type.setProduct(product);
        }
        typeService.saveAll(types);
    }

    @Override
    public TypeDto edit(TypeDto typeDto) {
        Type type = typeService.findByUuid(typeDto.getUuid());
        convertDtoToEntity(typeDto, type);
        typeService.save(type);

        return convertEntityToDto(type);
    }

    @Override
    public void delete(TypeDto typeDto) {
        typeService.deleteByUuid(typeDto.getUuid());
    }

    @Override
    public void delete(String typeUuid) {
        typeService.deleteByUuid(typeUuid);
    }

    @Override
    public void setUuid(Type type) {
        type.setUuid(UUID.randomUUID().toString());
        if (type.getAmount()!=null) {
            amountFacade.setUuid(type.getAmount());
        }

        if (type.getMedias()!=null) {
            type.getMedias().forEach(mediaFacade::setUuid);
        }
    }

    private TypeDto convertEntityToDto(Type type) {
        return modelMapper.map(type, TypeDto.class);
    }


    private void convertDtoToEntity(TypeDto typeDto, Type type) {
        modelMapper.map(typeDto, type);
    }
}
