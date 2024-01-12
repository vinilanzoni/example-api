package com.example.api.repository;

import com.example.api.domain.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAllByCustomer(Long customerId);

    List<Address> findAllByCityLikeIgnoreCase(String city);

    List<Address> findAllByStateLikeIgnoreCase(String state);
}
