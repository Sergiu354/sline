package com.sline.sline.repository.project.person;

import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Phone findByUuid(String uuid);
    Phone findByNumber(Integer number);
    Phone findByPerson(Person person);
    Phone findByPerson_Uuid(String person_uuid);
    void deleteByUuid(String uuid);
}
