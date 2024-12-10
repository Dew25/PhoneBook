package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.helpers.AppHelper;
import ee.ivkhkdev.phonebook.helpers.PersonHelper;
import ee.ivkhkdev.phonebook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements AppService<Person>{

    @Autowired
    private AppHelper<Person> personHelper;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean add() {
        Optional<Person> optionalPerson = personHelper.create();
        if(optionalPerson.isPresent()){
            personRepository.save(optionalPerson.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public void printAll() {
        personHelper.printList(personRepository.findAll());
    }

    @Override
    public void printOne() {

        List<Person> persons = this.findByFirstnameAndLastname();
        if(!persons.isEmpty()){
            personHelper.printList(persons);
        }
    }

    @Override
    public boolean delete() {
        return false;
    }


    private List<Person> findByFirstnameAndLastname() {
        List<String> firstnameAndLastname = personHelper.findPersonNames();
        return personRepository.findByFirstnameAndLastname(
                firstnameAndLastname.get(0),
                firstnameAndLastname.get(1)
        );
    }
}
