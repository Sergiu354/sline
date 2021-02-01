package com.sline.sline.service.project.product.amount;

import com.sline.sline.entity.project.protuct.Amount;
import com.sline.sline.entity.project.protuct.Type;

public interface AmountService {
    Amount findById(Long id);
    Amount findByUuid(String uuid);
    Amount findByType(Type type);
    Amount findByTypeUuid(String typeUuid);
    void save(Amount amount);
    Amount edit(Amount amount);
    void delete(Amount amount);
    void delete(String uuid);
}
