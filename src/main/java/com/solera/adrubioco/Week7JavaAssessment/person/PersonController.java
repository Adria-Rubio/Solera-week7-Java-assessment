package com.solera.adrubioco.Week7JavaAssessment.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Get a list of threads.
     *
     * @return the complete list of threads
     */
    @RequestMapping("/persons")
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @RequestMapping("/person/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Person person = personService.getPerson(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>("Thread does not exist", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/person/new")
    public ResponseEntity<?> create(@RequestBody Person person) {
        return personService.createPerson(person)
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/person/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return personService.deletePerson(id)
                ? new ResponseEntity<>("Thread deleted successfully", HttpStatus.OK)
                : new ResponseEntity<>("Thread doesn't exist", HttpStatus.NO_CONTENT);
    }

}
