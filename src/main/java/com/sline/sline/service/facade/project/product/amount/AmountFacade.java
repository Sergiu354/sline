package com.sline.sline.service.facade.project.product.amount;

import com.sline.sline.dto.product.AmountDto;
import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.entity.project.protuct.Amount;

public interface AmountFacade {
    AmountDto findByUuid(String uuid);
    AmountDto findByType(TypeDto typeDto);
    AmountDto findByTypeUuid(String typeUuid);
    AmountDto save(AmountDto amountDto);
    AmountDto save(String typeUuid, AmountDto amountDto);
    AmountDto edit(AmountDto amountDto);
    void delete(AmountDto amountDto);
    void delete(String amountUuid);
    void setUuid(Amount amount);
}
