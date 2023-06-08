package com.alex.practice.CRUDpractice.repository;

import com.alex.practice.CRUDpractice.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {


    public Person findByEmail(String email);

}
