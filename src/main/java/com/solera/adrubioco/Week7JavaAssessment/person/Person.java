package com.solera.adrubioco.Week7JavaAssessment.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}