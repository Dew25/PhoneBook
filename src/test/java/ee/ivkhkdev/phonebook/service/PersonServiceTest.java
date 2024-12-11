package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.helpers.AppHelper;
import ee.ivkhkdev.phonebook.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private AppHelper<Person> personHelper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_shouldAddPersonSuccessfully() {
        // Arrange
        Person person = new Person("John", "Doe", "123456789");
        when(personHelper.create()).thenReturn(Optional.of(person));

        // Act
        boolean result = personService.add();

        // Assert
        assertTrue(result);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void add_shouldReturnFalseWhenPersonNotCreated() {
        // Arrange
        when(personHelper.create()).thenReturn(Optional.empty());

        // Act
        boolean result = personService.add();

        // Assert
        assertFalse(result);
        verify(personRepository, never()).save(any());
    }

    @Test
    void printAll_shouldPrintAllPersonsSuccessfully() {
        // Arrange
        List<Person> persons = List.of(
                new Person("John", "Doe", "123456789"),
                new Person("Jane", "Smith", "987654321")
        );
        when(personRepository.findAll()).thenReturn(persons);

        // Act
        personService.printAll();

        // Assert
        verify(personHelper, times(1)).printList(persons);
    }

    @Test
    void printOne_shouldPrintPersonSuccessfully() {
        // Arrange
        List<String> names = List.of("John", "Doe");
        List<Person> persons = List.of(new Person("John", "Doe", "123456789"));

        when(personHelper.findPersonNames()).thenReturn(names);
        when(personRepository.findByFirstnameAndLastname("John", "Doe")).thenReturn(persons);

        // Act
        personService.printOne();

        // Assert
        verify(personHelper, times(1)).findPersonNames();
        verify(personRepository, times(1)).findByFirstnameAndLastname("John", "Doe");
        verify(personHelper, times(1)).printList(persons);
    }

    @Test
    void printOne_shouldHandleNoPersonsFound() {
        // Arrange
        List<String> names = List.of("Nonexistent", "User");
        List<Person> persons = new ArrayList<>();

        when(personHelper.findPersonNames()).thenReturn(names);
        when(personRepository.findByFirstnameAndLastname("Nonexistent", "User")).thenReturn(persons);

        // Act
        personService.printOne();

        // Assert
        verify(personHelper, times(1)).findPersonNames();
        verify(personRepository, times(1)).findByFirstnameAndLastname("Nonexistent", "User");
        verify(personHelper, never()).printList(any());
    }
}
