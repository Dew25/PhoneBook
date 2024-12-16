package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.entity.Person;
import ee.ivkhkdev.phonebook.helpers.AddressHelperImpl;
import ee.ivkhkdev.phonebook.helpers.PersonHelper;
import ee.ivkhkdev.phonebook.repository.AddressRepository;
import ee.ivkhkdev.phonebook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonHelper personHelper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressHelperImpl addressHelperImpl;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Override
    public boolean add() {
        Optional<Person> optionalPerson = personHelper.create();//без адреса
        if(optionalPerson.isEmpty()){
            return false;
        }
        Person person = optionalPerson.get();//без адреса
        try {
            Long addressId = addressHelperImpl.getAddressId(addressRepository.findAll());
            if(addressId == 0) return false;
            Optional<Address> optionalAddress = addressRepository.findById(addressId);
            if(optionalAddress.isEmpty()) return false;
            person.setAddress(optionalAddress.get());// с адресом
            personRepository.save(person);
        }catch (Exception e){
            return false;
        }
        return true;
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
        try {
            Long personIdForDelete = personHelper.findPesonId(personRepository.findAll());
            Optional<Person> optionalPerson = personRepository.findById(personIdForDelete);
            if(optionalPerson.isEmpty()) return false;
            personRepository.delete(optionalPerson.get());
            try {
                addressRepository.delete(optionalPerson.get().getAddress());
            }catch (Exception e){
                System.out.println("Удалена персона");
            }
            return true;
        }catch (Exception e){
            return false;
        }

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
