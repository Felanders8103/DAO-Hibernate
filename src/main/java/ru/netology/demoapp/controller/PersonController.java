package ru.netology.demoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.demoapp.entity.Person;
import ru.netology.demoapp.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> findByCityOfLiving(@RequestParam String city) {
        return repository.findByCityOfLiving(city);
    }

    @GetMapping("/persons/by-age")
    public List<Person> getPersonsByAgeLessThan(@RequestParam Integer age) {
        return repository.findByAgeLessThanOrderByAgeAsc(age);
    }

    @GetMapping("/persons/by-name-surname")
    public Optional<Person> getPersonByNameAndSurname(@RequestParam String name,
                                                      @RequestParam String surname) {
        return repository.findByNameAndSurname(name, surname);
    }
}
