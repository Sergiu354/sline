package com.sline.sline.service.facade.project.company;

import com.sline.sline.dto.company.CompanyDto;
import com.sline.sline.dto.order.OrderDto;
import com.sline.sline.entity.project.company.Company;
import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.system.User;
import com.sline.sline.service.project.company.CompanyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyFacadeImpl implements CompanyFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final CompanyService companyService;

    @Override
    public CompanyDto save(CompanyDto companyDto) {
        Company company = new Company();
        convertDtoToEntity(companyDto, company);
        companyService.save(company);
        return convertEntityToDto(company);
    }

    @Override
    public CompanyDto createNewCompany(String companyName) {
        Company company = new Company();
        company.setName(companyName);
        companyService.save(company);
        return convertEntityToDto(company);
    }

    @Override
    public boolean addUser(String companyUuid, String userUuid) {
        return false;
    }

    @Override
    public boolean removeUser(String companyUuid, String userUuid) {
        Company company = companyService.findByUuid(userUuid);
        if (company!=null) {
            User user = company.getUsers().stream().filter(user1 -> user1.getUuid().equals(userUuid)).findFirst().orElse(null);
            if (user!=null) {
                company.getUsers().remove(user);
                companyService.save(company);
                return true;
            } else {
                LOGGER.info("Not find user in company by UUID company: {} and UUID user: {} " + companyUuid, userUuid);
                return false;
            }
        }
        LOGGER.info("Not find company by UUID: " + companyUuid);
        return false;
    }

    @Override
    public boolean checkUserToCompany(String companyUuid, String userUuid) {
        Company company = companyService.findByUuid(userUuid);
        if (company!=null) {
            return company.getUsers().stream().anyMatch(user -> user.getUuid().equals(userUuid));
        }
        return false;
    }

    @Override
    public void delete(CompanyDto companyDto) {
        Company company = companyService.findByUuid(companyDto.getUuid());
        LOGGER.info("Delete company Name: {} and UUID: {}", company.getName(), company.getUuid());
        companyService.delete(company);
    }

    private CompanyDto convertEntityToDto(Company company) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(company, CompanyDto.class);
    }

    private void convertDtoToEntity(CompanyDto companyDto, Company company) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<CompanyDto, Company>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
                skip(destination.getProducts());
                skip(destination.getOrders());
                skip(destination.getUsers());
            }
        });
        modelMapper.map(companyDto, company);
    }
}
