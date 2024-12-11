package ee.ivkhkdev.phonebook.service;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.helpers.AddressHelperImpl;
import ee.ivkhkdev.phonebook.repository.AddressRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AddressServiceImpl implements AddressService{
    private final AddressHelperImpl addressHelper;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressHelperImpl addressHelper, AddressRepository addressRepository) {
        this.addressHelper=addressHelper;
        this.addressRepository = addressRepository;
    }

    @Override
    public boolean add() {
        Optional<Address> optionalAddress = addressHelper.create();
        if(optionalAddress.isEmpty()){
            return false;
        }
        addressRepository.save(optionalAddress.get());
        return true;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public void printAll() {

    }

    @Override
    public void printById() {

    }

    @Override
    public boolean delete() {
        return false;
    }
}
