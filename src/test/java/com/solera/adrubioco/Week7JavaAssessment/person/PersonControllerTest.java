package com.solera.adrubioco.Week7JavaAssessment.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    private final Map<Integer, Person> fakeDb = new HashMap<>();

    @SneakyThrows
    public static String asJsonString(final Object obj) {
            return new ObjectMapper().writeValueAsString(obj);
    }

    @Test
    public void getAllShouldReturnListOfPersonsFromService() throws Exception {
        Person expected = new Person(1, "John", "Do", "123456789", "johndo@foo.com");

        fakeDb.put(expected.getId(), expected);
        when(service.getAllPersons())
                .thenReturn(fakeDb.values().stream().toList());

        assertNotNull(fakeDb.values().stream().toList().get(0));
        this.mockMvc.perform(get("/api/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Integer.toString(expected.getId()))));
    }

    @Test
    public void getSinglePersonShouldReturnPersonFromService() throws Exception {
        Person expected = new Person(1, "John", "Do", "123456789", "johndo@foo.com");
        fakeDb.put(expected.getId(), expected);

        when(service.getPerson(anyInt())).thenReturn(expected);

        assertNotNull(service.getPerson(expected.getId()));
        this.mockMvc.perform(get("/api/person/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Integer.toString(expected.getId()))));
    }

    @Test
    @DisplayName("Should return 201 when calling POST /api/person/new with Person json")
    public void createPersonShouldReturn201WhenPersonCreated() throws Exception {
        Person expected = new Person(1, "John", "Do", "123456789", "johndo@foo.com");

        when(service.createPerson(Mockito.any(Person.class)))
                .then(invocation -> {
                    fakeDb.put(expected.getId(), expected);
                    return fakeDb.containsKey(expected.getId());
                });

        this.mockMvc.perform(MockMvcRequestBuilders
                                .post("/api/person/new")
                                .content(asJsonString(expected))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        System.out.println(asJsonString(expected));
        System.out.println("Reach this");
        System.out.println(fakeDb);
        assertFalse(fakeDb.isEmpty());
    }

    @Test
    @DisplayName("Should return 200 when calling POST /api/person/{id}/delete")
    public void deletePersonShouldReturn200WhenPersonDeleted() throws Exception {
        Person newPerson = new Person(1, "John", "Do", "123456789", "johndo@foo.com");
        fakeDb.put(newPerson.getId(), newPerson);

        when(service.deletePerson(anyInt()))
                .then(invocation -> {
                    fakeDb.remove(newPerson.getId());
                    return !fakeDb.containsKey(newPerson.getId());
                });

        assertFalse(fakeDb.isEmpty());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/person/"+ newPerson.getId()+"/delete"))
                .andDo(print())
                .andExpect(status().isOk());

        assertTrue(fakeDb.isEmpty());
    }
}
