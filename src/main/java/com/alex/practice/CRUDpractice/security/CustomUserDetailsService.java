package com.alex.practice.CRUDpractice.security;

import com.alex.practice.CRUDpractice.model.Person;
import com.alex.practice.CRUDpractice.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public CustomUserDetailsService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByEmail(username); //todo: Почему null?
        System.out.println(username);
        System.out.println(user.getUsername());
        if(user != null){

            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()

            );

        } else {
            System.out.println("Я ТУТ!");
            throw new UsernameNotFoundException("Неверный логин или пароль");
        }
    }
}
