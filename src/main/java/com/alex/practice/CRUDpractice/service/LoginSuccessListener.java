package com.alex.practice.CRUDpractice.service;

import com.alex.practice.CRUDpractice.model.Person;
import com.alex.practice.CRUDpractice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {

//         if you just need the login
        String login = evt.getAuthentication().getName();
        System.out.println(login + " has just logged in");
        Person logPerson = personRepository.findByEmail(login);
        logPerson.addCurrentEntryTime();
        personService.updatePerson(logPerson);



    }
}