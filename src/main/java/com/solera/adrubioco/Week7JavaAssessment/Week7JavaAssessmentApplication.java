package com.solera.adrubioco.Week7JavaAssessment;

import com.solera.adrubioco.Week7JavaAssessment.person.Person;
import com.solera.adrubioco.Week7JavaAssessment.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Week7JavaAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week7JavaAssessmentApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner bookDemo(PersonRepository repository) {
//		return (args) -> {
//			repository.save(new Person(1, "John", "Do", "123456789", "johndo@foo.com"));
//		};
//	}
}
