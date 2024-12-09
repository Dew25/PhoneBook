package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.input.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonHelper implements AppHelper<Person> {
    @Autowired
    private Input input;
    @Override
    public Optional create() {
        try {
            Person person = new Person();
            System.out.println("Имя: ");
            person.setFirstname(input.nextLine());
            System.out.println("Фамилия: ");
            person.setLastname(input.nextLine());
            System.out.println("Телефон: ");
            person.setPhoneNumber(input.nextLine());
            return Optional.of(person);
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public Optional update(List<Person> persons) {
        return Optional.empty();
    }

    @Override
    public Long remove(List<Person> persons) {
        return 0L;
    }

    @Override
    public boolean print(Person person) {
        return false;
    }

    @Override
    public boolean printList(List<Person> persons) {
        if(persons.isEmpty()){
            System.out.println("==== Список пуст ====");
            return false;
        }
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            System.out.printf("%d. %s %s. %s%n",
                    person.getId(),
                    person.getFirstname(),
                    person.getLastname(),
                    person.getPhoneNumber()
            );
        }
        return true;
    }

    @Override
    public List<String> findPersonNames() {
        List<String> names = new ArrayList<>();
        System.out.print("Имя: ");
        names.add(input.nextLine());
        System.out.print("Фамилия: ");
        names.add(input.nextLine());
        return names;
    }
}
