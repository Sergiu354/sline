package com.sline.sline.service.project.person.phone;

import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;

public interface PhoneService {
    Phone findById(Long id);
    Phone findByUuid(String uuid);
    Phone findByNumber(Integer number);
    Phone findByPerson(Person person);
    Phone findByPersonUuid(String uuid);
    void save(Phone phone);
    void delete(Phone phone);
    void delete(String uuid);
}
