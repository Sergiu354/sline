package com.sline.sline.service.project.product.product;

import com.sline.sline.entity.project.protuct.Amount;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.repository.project.product.ProductRepository;
import com.sline.sline.session.DataSession;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        boolean isAmount = false;
        if (product.getTypes()!=null && !product.getTypes().isEmpty()) {
            for (Amount amount : product.getTypes().stream().filter(type -> type.getAmount()!=null).map(Type::getAmount).collect(Collectors.toList())) {
                if (amount.getAmount()>0) {
                    isAmount = true;
                    break;
                }
            }
        }
        product.setAmount(isAmount);
        productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Long companyId, Pageable pageable) {
        return productRepository.findAllByCompany_Id(companyId, pageable);
    }

    @Override
    public Page<Product> findAllByInStock(Integer stockSize, Long companyId, Pageable pageable) {
        if (stockSize==0) {
            return productRepository.findAllByAmountIsFalse(companyId,  pageable);
        }
        return productRepository.findAllByAmount(stockSize, companyId, pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product findByUuid(String uuid, Long companyId) {
        return productRepository.findByUuidAndCompany_Id(uuid, companyId);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(String uuid) {
        productRepository.deleteByUuid(uuid);
    }
}
