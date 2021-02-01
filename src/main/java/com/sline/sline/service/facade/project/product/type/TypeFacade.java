package com.sline.sline.service.facade.project.product.type;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.entity.project.protuct.Type;

import java.util.List;

public interface TypeFacade {
    TypeDto findByUuid(String uuid);
    List<TypeDto> findAllByByProduct(ProductDto productDto);
    List<TypeDto> findAllByByProductUuid(String productUuid);
    void save(TypeDto typeDto);
    void save(String productUuid, TypeDto typeDto);
    void save(String productUuid, List<TypeDto> typeDtos);
    TypeDto edit(TypeDto typeDto);
    void delete(TypeDto typeDto);
    void delete(String typeUuid);
    void setUuid(Type type);
}
