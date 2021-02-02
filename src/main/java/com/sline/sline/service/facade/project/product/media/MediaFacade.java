package com.sline.sline.service.facade.project.product.media;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.MediaDto;
import com.sline.sline.entity.project.protuct.Media;

import java.util.List;

public interface MediaFacade {
    MediaDto findByUuid(String uuid);
    List<MediaDto> findAllByType(TypeDto typeDto);
    List<MediaDto> findAllByTypeUuid(String uuid);
    MediaDto save(MediaDto mediaDto);
    MediaDto save(String typeUuid, MediaDto mediaDto);
    MediaDto edit(MediaDto mediaDto);
    void delete(MediaDto mediaDto);
    void delete(String uuid);
    void setUuid(Media media);
}
