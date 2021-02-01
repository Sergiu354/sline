package com.sline.sline.service.facade.project.product.image;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.ImageDto;
import com.sline.sline.entity.project.protuct.Image;

import java.util.List;

public interface ImageFacade {
    ImageDto findByUuid(String uuid);
    List<ImageDto> findAllByType(TypeDto typeDto);
    List<ImageDto> findAllByTypeUuid(String uuid);
    ImageDto save(ImageDto imageDto);
    ImageDto save(String typeUuid, ImageDto imageDto);
    ImageDto edit(ImageDto imageDto);
    void delete(ImageDto imageDto);
    void delete(String uuid);
    void setUuid(Image image);
}
