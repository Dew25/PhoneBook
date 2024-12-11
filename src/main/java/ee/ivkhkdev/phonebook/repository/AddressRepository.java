package ee.ivkhkdev.phonebook.repository;

import ee.ivkhkdev.phonebook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
