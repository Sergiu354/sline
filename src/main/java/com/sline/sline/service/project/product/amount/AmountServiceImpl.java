package com.sline.sline.service.project.product.amount;

import com.sline.sline.entity.project.protuct.Amount;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.repository.project.product.AmountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmountServiceImpl implements AmountService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final AmountRepository amountRepository;

    @Override
    public Amount findById(Long id) {
        return amountRepository.findById(id).orElse(null);
    }

    @Override
    public Amount findByUuid(String uuid) {
        return amountRepository.findByUuid(uuid);
    }

    @Override
    public Amount findByType(Type type) {
        return findByTypeUuid(type.getUuid());
    }

    @Override
    public Amount findByTypeUuid(String typeUuid) {
        return amountRepository.findByType_Uuid(typeUuid);
    }

    @Override
    public void save(Amount amount) {
        amountRepository.save(amount);
    }

    @Override
    public Amount edit(Amount amount) {
        return amountRepository.save(amount);
    }

    @Override
    public void delete(Amount amount) {
        amountRepository.delete(amount);
    }

    @Override
    public void delete(String uuid) {
        amountRepository.deleteByUuid(uuid);
    }
}
