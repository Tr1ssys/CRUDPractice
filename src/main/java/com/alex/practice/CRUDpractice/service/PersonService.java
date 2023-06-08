package com.alex.practice.CRUDpractice.service;

import com.alex.practice.CRUDpractice.model.EntryTime;
import com.alex.practice.CRUDpractice.model.Person;
import com.alex.practice.CRUDpractice.model.Role;
import com.alex.practice.CRUDpractice.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {


    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder){
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findById(Long id){
        return personRepository.getOne(id);
    }

    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Page<Person> findAll(Pageable pageable){
        return personRepository.findAll(pageable);
    }

    public void createPerson(Person person){
        String password = person.getPassword();
        person.setPassword(passwordEncoder.encode(password));
        person.setRole(Role.USER);
        personRepository.save(person);
    }

    public void updatePerson(Person person){
        personRepository.save(person);
    }
    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }


//    public void addEntryTime(Person person){
//        person.addEntryTimeToPerson();
//    }
}
