package com.sline.sline.service.project.person.phone;

import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.person.Phone;
import com.sline.sline.repository.project.person.PhoneRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final PhoneRepository phoneRepository;

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    @Override
    public Phone findByUuid(String uuid) {
        return phoneRepository.findByUuid(uuid);
    }

    @Override
    public Phone findByNumber(Integer number) {
        return findByNumber(number);
    }

    @Override
    public Phone findByPerson(Person person) {
        return findByPerson(person);
    }

    @Override
    public Phone findByPersonUuid(String uuid) {
        return phoneRepository.findByPerson_Uuid(uuid);
    }

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void delete(Phone phone) {
        phoneRepository.delete(phone);
    }

    @Override
    public void delete(String uuid) {
        phoneRepository.deleteByUuid(uuid);
    }
}
