package com.sline.sline.service.project.person.person;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;
import com.sline.sline.repository.project.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final PersonRepository personRepository;

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person findByUuid(String uuid) {
        return personRepository.findByUuid(uuid);
    }

    @Override
    public Person findByIDNO(String idno) {
        return personRepository.findByIdno(idno);
    }

    @Override
    public Person findByOrder(Order order) {
        return personRepository.findByOrder(order);
    }

    @Override
    public Person findByOrderUuid(String orderUuid) {
        return personRepository.findByOrder_Uuid(orderUuid);
    }

    @Override
    public Person findByAddress(Address address) {
        return personRepository.findByAddress(address);
    }

    @Override
    public Person findByAddressUuid(String addressUuid) {
        return personRepository.findByAddress_Uuid(addressUuid);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findAllByCompanyName(String companyName) {
        return personRepository.findAllByCompanyName(companyName);
    }

    @Override
    public List<Person> findAllIsCompany(boolean isCompany) {
        return personRepository.findAllByCompany(isCompany);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void delete(String uuid) {
        personRepository.deleteByUuid(uuid);
    }
}
