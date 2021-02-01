package com.sline.sline.service.project.product.video;

import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Video;

import java.util.List;

public interface VideoService {
    Video findById(Long id);
    Video findByUuid(String uuid);
    List<Video> findAllByType(Type type);
    List<Video> findAllByTypeUuid(String typeUuid);
    void save(Video video);
    void delete(Video video);
    void delete(String uuid);
}
