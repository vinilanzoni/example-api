package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
        }
        return customer;
    }

    public List<Customer> findAllPaginated(String name, String email, String gender, String city, String state, Integer pageNumber, Integer pageSize) {

        name = name == null ? "" : name;
        email = email == null ? "" : email;
        gender = gender == null ? "" : gender;
        city = city == null ? "" : city;
        state = state == null ? "" : state;

        List<Customer> customers;
        Pageable page;

        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        } else {
            page = Pageable.unpaged();
        }

        if (!city.isBlank() || !state.isBlank()) {
            customers = customerRepository.findAllWithAddressByParameters(name, email, gender, city, state, page);
        } else {
            customers = customerRepository.findAllByParameters(name, email, gender, page);
        }

        return customers;
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

}
