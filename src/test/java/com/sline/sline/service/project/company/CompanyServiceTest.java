package com.sline.sline.service.project.company;

import com.sline.sline.entity.project.company.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    void save() {
        Company company = new Company();
        company.setName("Test Company");
        companyService.save(company);
        System.out.println(company);
    }

    @Test
    void findByUuid() {
    }
}
