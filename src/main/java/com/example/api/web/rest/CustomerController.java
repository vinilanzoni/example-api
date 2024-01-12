package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.AddressDto;
import com.example.api.dto.CustomerDto;
import com.example.api.service.AddressService;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final AddressService addressService;

    @Autowired
    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody CustomerDto customer) {
        return customerService.create(customer.toEntity());
    }

    @PostMapping("/{customerId}/addresses")
    public Address createAddress(@PathVariable Long customerId, @Valid @RequestBody AddressDto address) {
        return addressService.create(customerId, address.toEntity());
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable Long id) {
        return customerService.delete(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @GetMapping
    public List<Customer> findAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "page_number", required = false) Integer pageNumber,
            @RequestParam(name = "page_size", required = false) Integer pageSize
    ) {
        return customerService.findAllPaginated(name, email, gender, city, state, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

}
