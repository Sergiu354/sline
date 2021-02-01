package com.sline.sline.service.project.product.image;

import com.sline.sline.entity.project.protuct.Image;

import java.util.List;

public interface ImageService {
    Image findById(Long id);
    Image findByUuid(String uuid);
    List<Image> findAllByTypeUuid(String typeUuid);
    void save(Image image);
    void delete(Image image);
    void delete(String uuid);
}
