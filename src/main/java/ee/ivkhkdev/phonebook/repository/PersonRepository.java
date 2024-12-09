package ee.ivkhkdev.phonebook.repository;

import ee.ivkhkdev.phonebook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstnameAndLastname(String firstname, String lastname);
}
