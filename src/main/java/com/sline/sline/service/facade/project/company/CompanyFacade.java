package com.sline.sline.service.facade.project.company;

import com.sline.sline.dto.company.CompanyDto;
import com.sline.sline.dto.user.UserDto;

public interface CompanyFacade {
    CompanyDto save(CompanyDto company);
    CompanyDto findByUuid(String uuid);
    CompanyDto createNewCompany(String companyName);
    default boolean addUser(CompanyDto company, UserDto user) {
        return addUser(company.getUuid(), user.getUuid());
    }
    boolean addUser(String companyUuid, String userUuid);
    default boolean removeUser(CompanyDto company, UserDto user) {
        return removeUser(company.getUuid(), user.getUuid());
    }
    boolean removeUser(String companyUuid, String userUuid);
    boolean checkUserToCompany(String companyUuid, String userUuid);
    void delete(CompanyDto company);
}
