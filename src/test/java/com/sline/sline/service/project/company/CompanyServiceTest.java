package com.sline.sline.service.project.company;

import com.sline.sline.entity.project.company.Company;
import com.sline.sline.entity.system.User;
import com.sline.sline.service.system.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;


    @Test
    void save() {
        Company company = new Company();
        company.setName("Test Company");
        companyService.save(company);
        User user = userService.findById((long) 1);
        user.getCompany().add(company);
        userService.save(user);
        System.out.println(company);
    }

    @Test
    void findByUuid() {
    }

    @Test
    void setUserToCompany() {
        User user = userService.findByUuid("e3ba1a89-3bc5-4541-ad8d-2d008a5c3375");
        Company company = companyService.findByUuid("af2f9651-d759-41a5-a278-dd37fee87301");
        company.getUsers().add(user);
        companyService.save(company);
    }
}
