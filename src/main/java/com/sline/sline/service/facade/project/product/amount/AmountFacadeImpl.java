package com.sline.sline.service.facade.project.product.amount;

import com.sline.sline.dto.product.AmountDto;
import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.entity.project.protuct.Amount;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.service.project.product.amount.AmountService;
import com.sline.sline.service.project.product.type.TypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AmountFacadeImpl implements AmountFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final AmountService amountService;
    private final TypeService typeService;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void construct() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<AmountDto, Amount>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }


    @Override
    public AmountDto findByUuid(String uuid) {
        Amount amount = amountService.findByUuid(uuid);
        if (amount==null) {
            LOGGER.info("Not found Amount find by UUID: " + uuid);
            return null;
        }
        return convertEntityToDto(amount);
    }

    @Override
    public AmountDto findByType(TypeDto typeDto) {
        return findByTypeUuid(typeDto.getUuid());
    }

    @Override
    public AmountDto findByTypeUuid(String typeUuid) {
        Amount amounts = amountService.findByTypeUuid(typeUuid);
        if (amounts==null) {
            LOGGER.info("Not found Amount by type UUID: " + typeUuid);
            return null;
        }
        return convertEntityToDto(amounts);
    }

    @Override
    public AmountDto save(AmountDto amountDto) {
        return save(amountDto.getType().getUuid(), amountDto);
    }

    @Override
    public AmountDto save(String typeUuid, AmountDto amountDto) {
        Amount amount = new Amount();
        Type type = typeService.findByUuid(typeUuid);
        if (type !=null) {
            convertDtoToEntity(amountDto, amount);
            amount.setType(type);
            typeService.save(type);
            return convertEntityToDto(amount);
        } else
            LOGGER.info("Not found find by type UUID: " + typeUuid);
        return null;
    }

    @Override
    public AmountDto edit(AmountDto amountDto) {
        Amount amount = amountService.findByUuid(amountDto.getUuid());
        convertDtoToEntity(amountDto, amount);
        amountService.save(amount);
        return convertEntityToDto(amount);
    }

    @Override
    public void delete(AmountDto amountDto) {
        delete(amountDto.getUuid());
    }

    @Override
    public void delete(String amountUuid) {
        amountService.delete(amountUuid);
    }

    private AmountDto convertEntityToDto(Amount amount) {
        return modelMapper.map(amount, AmountDto.class);
    }

    private void convertDtoToEntity(AmountDto amountDto, Amount amount) {
        modelMapper.map(amountDto, amount);
    }

    @Override
    public void setUuid(Amount amount) {
        amount.setUuid(UUID.randomUUID().toString());
    }
}
