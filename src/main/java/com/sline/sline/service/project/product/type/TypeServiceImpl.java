package com.sline.sline.service.project.product.type;

import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.repository.project.product.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void saveAll(Set<Type> types) {
        typeRepository.saveAll(types);
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type findByUuid(String uuid) {
        return typeRepository.findByUuid(uuid);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> findByProductId(Long id) {
        return typeRepository.findAllByProduct_Id(id);
    }

    @Override
    public List<Type> findByProductUuid(String uuid) {
        return typeRepository.findAllByProduct_Uuid(uuid);
    }

    @Override
    public Product editById(Long id) {
        return null;
    }

    @Override
    public Product editByUuid(String uuid) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(String uuid) {
        typeRepository.deleteByUuid(uuid);
    }
}
