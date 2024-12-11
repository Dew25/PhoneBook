package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.entity.Address;

import java.util.List;
import java.util.Optional;

public interface PersonHelper extends AppHelper<Person>{
    List<String> findPersonNames();
    Long findPersonId(List<Person> persons);
}
