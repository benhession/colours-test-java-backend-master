package com.answerdigital.colourstest.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.answerdigital.colourstest.dto.PersonUpdateDTO;
import com.answerdigital.colourstest.model.Person;
import com.answerdigital.colourstest.repository.PeopleRepository;

@RestController
@RequestMapping("/People")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRespository;

    private final Logger logger = LoggerFactory.getLogger("People Controller");

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        // STEP 1
        //
        // Implement a JSON endpoint that returns the full list
        // of people from the PeopleRepository. If there are zero
        // people returned from PeopleRepository then an empty
        // JSON array should be returned.

        return new ResponseEntity<>(peopleRespository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        // Step 2
        //
        // Implement a JSON endpoint that returns a single person
        // from the PeopleRepository based on the id parameter.
        // If null is returned from the PeopleRepository with
        // the supplied id then a NotFound should be returned.

        Optional<Person> thePerson = peopleRespository.findById(id);

        return thePerson
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody PersonUpdateDTO personUpdate) {
        // STEP 3
        //
        // Implement an endpoint that receives a JSON object to
        // update a person using the PeopleRepository based on
        // the id parameter. Once the person has been successfully
        // updated, the person should be returned from the endpoint.
        // If null is returned from the PeopleRepository then a
        // NotFound should be returned.

        Optional<Person> thePerson = peopleRespository.findById(id);

        if (thePerson.isPresent()) {
            Person person = thePerson.get();

            person.setAuthorised(personUpdate.isAuthorised());
            person.setEnabled(personUpdate.isEnabled());
            person.setColours(personUpdate.getColours());

            try {
                Person savedPerson = peopleRespository.save(person);

                return new ResponseEntity<>(savedPerson, HttpStatus.OK);

            } catch (Exception e) {
                logger.warn("Error saving person: ".concat(e.getMessage()));

                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
