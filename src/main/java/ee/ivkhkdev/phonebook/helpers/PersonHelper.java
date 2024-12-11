package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Person;

import java.util.List;

public interface PersonHelper extends AppHelper<Person>{
    List<String> findPersonNames();
    Long findPesonId(List<Person> all);
}
