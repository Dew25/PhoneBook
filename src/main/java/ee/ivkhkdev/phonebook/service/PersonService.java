package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Person;

public interface PersonService extends AppService<Person> {
    void printByName();

}
