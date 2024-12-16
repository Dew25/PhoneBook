package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonHelperTest {

    @Mock
    private Input input;

    @InjectMocks
    private PersonHelperImpl personHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldCreatePersonSuccessfully() {
        // Arrange
        when(input.nextLine()).thenReturn("John", "Doe", "123456789");

        // Act
        Optional<Person> result = personHelper.create();

        // Assert
        assertTrue(result.isPresent());
        Person person = result.get();
        assertEquals("John", person.getFirstname());
        assertEquals("Doe", person.getLastname());
        assertEquals("123456789", person.getPhoneNumber());
    }

    @Test
    void create_shouldReturnEmptyOptionalWhenExceptionThrown() {
        // Arrange
        when(input.nextLine()).thenThrow(new RuntimeException("Input error"));

        // Act
        Optional<Person> result = personHelper.create();

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void print_shouldPrintPersonSuccessfully() {
        // Arrange
        Person person = new Person();
        person.setId(1L);
        person.setFirstname("John");
        person.setLastname("Doe");
        person.setPhoneNumber("123456789");

        // Act
        boolean result = personHelper.print(person);

        // Assert
        assertFalse(result); // Метод всегда возвращает false
    }

    @Test
    void printList_shouldPrintPersonListSuccessfully() {
        // Arrange
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("John", "Doe", "123456789",new Address());
        Person person2 = new Person("Jane", "Smith", "987654321",new Address());
        persons.add(person1);
        persons.add(person2);

        // Act
        boolean result = personHelper.printList(persons);

        // Assert
        assertTrue(result);
    }

    @Test
    void printList_shouldReturnFalseWhenListIsEmpty() {
        // Arrange
        List<Person> emptyList = new ArrayList<>();

        // Act
        boolean result = personHelper.printList(emptyList);

        // Assert
        assertFalse(result);
    }

    @Test
    void findPersonNames_shouldReturnCorrectNames() {
        // Arrange
        when(input.nextLine()).thenReturn("John", "Doe","123456");

        // Act
        List<String> result = personHelper.findPersonNames();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0));
        assertEquals("Doe", result.get(1));
    }
}
