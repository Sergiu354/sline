package com.sline.sline.service.project.person.person;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;

import java.util.List;

public interface PersonService {
    Person findById(Long id);
    Person findByUuid(String uuid);
    Person findByIDNO(String idno);
    Person findByOrder(Order order);
    Person findByOrderUuid(String orderUuid);
    Person findByAddress(Address address);
    Person findByAddressUuid(String addressUuid);
    List<Person> findAll();
    List<Person> findAllByCompanyName(String companyName);
    List<Person> findAllIsCompany(boolean isCompany);
    void save(Person person);
    void delete(Person person);
    void delete(String uuid);
}
