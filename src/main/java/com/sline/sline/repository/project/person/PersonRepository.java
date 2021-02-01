package com.sline.sline.repository.project.person;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUuid(String uuid);
    Person findByIdno(String idno);
    Person findByOrder(Order order);
    Person findByOrder_Uuid(String order_uuid);
    Person findByAddress(Address address);
    Person findByAddress_Uuid(String address_uuid);
    List<Person> findAllByCompanyName(String companyName);
    List<Person> findAllByCompany(Boolean company);
    void deleteByUuid(String uuid);
}
