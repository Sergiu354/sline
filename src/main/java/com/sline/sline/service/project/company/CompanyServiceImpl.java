package com.sline.sline.service.project.company;

import com.sline.sline.entity.project.company.Company;
import com.sline.sline.repository.project.company.CompanyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findByUuid(String uuid) {
        return companyRepository.findByUuid(uuid);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public void delete(String uuid) {
        companyRepository.deleteByUuid(uuid);
    }
}
