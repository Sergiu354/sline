package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Amount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountRepository extends JpaRepository<Amount, Long> {
    Amount findByUuid(String uuid);
    Amount findByType_Uuid(String typeUuid);
    void deleteByUuid(String uuid);
}
