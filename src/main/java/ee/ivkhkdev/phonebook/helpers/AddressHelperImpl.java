package ee.ivkhkdev.phonebook.helpers;

import ee.ivkhkdev.phonebook.entity.Address;
import ee.ivkhkdev.phonebook.input.Input;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class AddressHelperImpl implements AddressHelper{

    private final Input input;

    public AddressHelperImpl(Input input) {
        this.input=input;
    }

    @Override
    public Optional<Address> create() {
        try {
            Address address = new Address();
            System.out.println("Город: ");
            address.setCity(input.nextLine());
            System.out.println("Улица: ");
            address.setStreet(input.nextLine());
            System.out.println("Номер дома: ");
            address.setNumberHouse(input.nextLine());
            return Optional.of(address);
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public Optional<Address> update(List<Address> addresses) {
        return Optional.empty();
    }

    @Override
    public Long remove(List<Address> addresses) {
        return 0L;
    }

    @Override
    public boolean print(Address address) {
        return false;
    }

    @Override
    public boolean printList(List<Address> addresses) {
        if(addresses.isEmpty()){
            System.out.println("--- Список пуст ----");
            return false;
        }
        for (int i = 0; i < addresses.size(); i++) {
            System.out.printf("%d. %s. %s. %s%n",
                    addresses.get(i).getId(),
                    addresses.get(i).getCity(),
                    addresses.get(i).getStreet(),
                    addresses.get(i).getNumberHouse()
            );
        }
        return true;
    }

    @Override
    public Long getAddressId(List<Address> addresses) {
        this.printList(addresses);
        System.out.println("Выберите номер адреса или ноль: ");
        int addressId = input.nextInt();
        if(addressId == 0){
            System.out.println("Нет адреса");
        }
        return (long) addressId;
    }
}
