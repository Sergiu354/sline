package com.sline.sline.repository.project.company;

import com.sline.sline.entity.project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
