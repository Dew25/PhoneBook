package ee.ivkhkdev.phonebook.service;

import java.util.List;

public interface AppService<T> {
    boolean add();
    boolean edit();
    void printAll();
    void printById();
    boolean delete();
    //T findByFirstnameAndLastname();
}
