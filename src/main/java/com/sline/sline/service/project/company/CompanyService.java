package com.sline.sline.service.project.company;

import com.sline.sline.entity.project.company.Company;
import com.sline.sline.entity.system.User;
import org.springframework.stereotype.Service;

public interface CompanyService {
    Company save(Company company);
    Company findByUuid(String uuid);
    Company findById(Long id);
    void delete(Company company);
    void delete(String uuid);
}
