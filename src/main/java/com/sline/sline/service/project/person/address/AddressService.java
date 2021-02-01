package com.sline.sline.service.project.person.address;

import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;

import java.util.List;

public interface AddressService {
    Address findById(Long id);
    Address fidByUuid(String uuid);
    List<Address> findAllByPerson(Person person);
    List<Address> findAllByPersonUuid(String personUuid);
    void save(Address address);
    void delete(Address address);
    void delete(String uuid);
}
