package com.solera.adrubioco.Week7JavaAssessment.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPersons() {
        List<Person> result = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    public Person getPerson(int id) {
        return personRepository.findById(id);
    }


    /**
     * @param id The id of the user you want to delete
     * @return Could the user be deleted?
     */
    public boolean deletePerson(int id) {
        personRepository.removeById(id);
        return true;
    }


    /**
     * @param person The user you want to create
     * @return could the user be created?
     */
    public boolean createPerson(Person person) {
        boolean doesPersonExist = personRepository.existsById(person.getId());
        if (doesPersonExist)
            return false;
        personRepository.save(person);
        return true;
    }

}
