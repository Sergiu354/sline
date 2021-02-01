package com.sline.sline.service.project.product.type;

import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Product;

import java.util.List;
import java.util.Set;

public interface TypeService {
    Type save(Type type);
    void saveAll(Set<Type> types);
    Type findById(Long id);
    Type findByUuid(String uuid);
    List<Type> findAll();
    List<Type> findByProductId(Long id);
    List<Type> findByProductUuid(String uuid);
    Product editById(Long id);
    Product editByUuid(String uuid);
    void deleteById(Long id);
    void deleteByUuid(String uuid);
}
