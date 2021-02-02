package com.sline.sline.service.project.product.media;

import com.sline.sline.entity.project.protuct.Media;

import java.util.List;

public interface MediaService {
    Media findById(Long id);
    Media findByUuid(String uuid);
    List<Media> findAllByTypeUuid(String typeUuid);
    void save(Media media);
    void delete(Media media);
    void delete(String uuid);
}
