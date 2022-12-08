package com.solera.adrubioco.Week7JavaAssessment.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findById(int id);

    boolean existsById(int id);

    void removeById(int id);

}
