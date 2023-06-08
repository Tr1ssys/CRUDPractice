package com.alex.practice.CRUDpractice.controller;

import com.alex.practice.CRUDpractice.model.Person;
import com.alex.practice.CRUDpractice.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/registration")
    public String registration(Person person){
        return("registration");
    }

    @PostMapping("/registration")
    public String registrateUser(@Valid Person user, BindingResult result, Model model){
        if(result.hasErrors()){
            return("registration");
        }
        if(personService.findByEmail(user.getEmail()) != null){
            model.addAttribute("existError","User already exists!");
            return("registration");
        }
        personService.createPerson(user);
        return "redirect:/login";
    }

    //CRUD
    @GetMapping("/persons")
    public String personList(Model model, @PageableDefault(size = 5)@SortDefault("id")Pageable pageable){
        Page page = personService.findAll(pageable);
        model.addAttribute("data",page);
        return "person-list";
    }
    @GetMapping("/person-create")
    public String createUserForm(Person person){
        return "person-create";
    }
    @PostMapping("/person-create")
    public String createPerson(Person person){
        personService.createPerson(person);
        return "redirect:/persons";
    }

    @GetMapping("person-delete/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
        return "redirect:/persons";
    }

    @PostMapping("/person-update")
    public String updatePerson(Person person){
        personService.updatePerson(person);
        return "redirect:/persons";
    }
    @GetMapping("person-update/{id}")
    public String updatePersonForm(@PathVariable("id") Long id, Model model){
        Person person = personService.findById(id);
        model.addAttribute("person",person);
        return "person-update";
    }

}
