package com.sline.sline.service.project.person.address;

import com.sline.sline.entity.project.person.Address;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.repository.project.person.AddressRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address fidByUuid(String uuid) {
        return addressRepository.findByUuid(uuid);
    }

    @Override
    public List<Address> findAllByPerson(Person person) {
        return addressRepository.findAllByPerson(person);
    }

    @Override
    public List<Address> findAllByPersonUuid(String personUuid) {
        return addressRepository.findAllByPerson_Uuid(personUuid);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public void delete(String uuid) {
        addressRepository.deleteByUuid(uuid);
    }
}
