package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.entity.Person;

import java.util.List;
import java.util.Optional;

public interface AppHelper<T> {
    Optional<T> create();
    Optional<T> update(List<T> ts);
    Long remove(List<T> ts);
    boolean print(T t);
    boolean printList(List<T> ts);

}
