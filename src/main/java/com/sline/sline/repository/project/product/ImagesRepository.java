package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Image, Long> {
    Image findByUuid(String uuid);
    List<Image> findAllByType_Uuid(String typeUuid);
    void deleteByUuid(String uuid);
}
