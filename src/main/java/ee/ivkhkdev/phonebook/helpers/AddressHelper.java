package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Address;

import java.util.List;

public interface AddressHelper extends AppHelper<Address>{
    Long getAdderssId(List<Address> addresses);
}
