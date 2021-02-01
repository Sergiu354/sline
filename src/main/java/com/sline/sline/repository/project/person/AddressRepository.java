package com.sline.sline.repository.project.person;

import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUuid(String uuid);
    List<Address> findAllByPerson(Person person);
    List<Address> findAllByPerson_Uuid(String person_uuid);
    void deleteByUuid(String uuid);
}
