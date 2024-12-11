package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.input.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonHelperImpl implements PersonHelper {
    @Autowired
    private Input input;

    @Override
    public Optional<Person> create() {
        try {
            Person person = new Person();
            System.out.print("Имя: ");
            person.setFirstname(input.nextLine());
            System.out.print("Фамилия: ");
            person.setLastname(input.nextLine());
            System.out.print("Телефон: ");
            person.setPhoneNumber(input.nextLine());
            Optional<Address>optionalAddress = this.getAddress();
            if(optionalAddress.isEmpty()){
                return Optional.empty();
            }
            person.setAddress(optionalAddress.get());
            return Optional.of(person);
        }catch (Exception e){
            return Optional.empty();
        }

    }

    private Optional<Address> getAddress(List<Address> addresses) {
        Optional<Address>
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
        System.out.printf("%d. %s %s. %s%n",
                person.getId(),
                person.getFirstname(),
                person.getLastname(),
                person.getPhoneNumber()
        );
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
            System.out.printf("%d. %s %s.%n",
                    person.getId(),
                    person.getFirstname(),
                    person.getLastname()
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

    @Override
    public Long findPesonId(List<Person> pesrons) {
        this.printList(pesrons);
        System.out.print("Выберите номер контакта: ");
        return (long) input.nextInt();
    }
}
