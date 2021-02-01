package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByUuid(String uuid);
    List<Type> findAllByProduct_Id(Long product_id);
    List<Type> findAllByProduct_Uuid(String product_uuid);
    void deleteByUuid(String uuid);
}
