package com.sline.sline.service.project.person.person;

import com.sline.sline.entity.project.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void findById() {
    }

    @Test
    void findByUuid() {
    }

    @Test
    void findByIDNO() {
    }

    @Test
    void findByOrder() {
    }

    @Test
    void findByOrderUuid() {
    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByAddressUuid() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByCompanyName() {
    }

    @Test
    void findAllIsCompany() {
    }

    @Test
    void save() {
        Person person = new Person();
        person.setFirstName("Sergiu");
        person.setLastName("Ionita");
        person.setIdno("065982514875454");
        personService.save(person);
        System.out.println(person);
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}
