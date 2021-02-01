package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findByUuid(String uuid);
    List<Video> findAllByType_Uuid(String typeUuid);
    void deleteByUuid(String uuid);
}
