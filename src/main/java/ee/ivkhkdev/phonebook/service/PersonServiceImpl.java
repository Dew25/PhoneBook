package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.helpers.PersonHelper;
import ee.ivkhkdev.phonebook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonHelper personHelper;
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
    public void printByName() {
        List<Person> persons = this.findByFirstnameAndLastname();
        if(!persons.isEmpty()){
            personHelper.printList(persons);
        }
    }

    @Override
    public void printById(){
        Optional<Person> optionalPerson = this.findById();
        if(optionalPerson.isPresent()) {
            personHelper.print(optionalPerson.get());
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
    private Optional<Person> findById(){
        Long personId = personHelper.findPesonId(personRepository.findAll());
        return personRepository.findById(personId);
    }
}
