package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByUuid(String uuid);
    List<Media> findAllByType_Uuid(String typeUuid);
    void deleteByUuid(String uuid);
}
