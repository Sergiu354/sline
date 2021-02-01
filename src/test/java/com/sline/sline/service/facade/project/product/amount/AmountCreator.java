package com.sline.sline.service.facade.project.product.amount;

import com.sline.sline.dto.product.AmountDto;
import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.service.facade.project.Generator;

import java.util.UUID;

public abstract class AmountCreator extends Generator {
    private static AmountDto amount;

    public static AmountDto createAmount(TypeDto typeDto) {
        amount = new AmountDto();
        amount.setUuid(UUID.randomUUID().toString());
        amount.setType(typeDto);
        amount.setAmount(getRandomNumber(2,30));
        return amount;
    }
}
