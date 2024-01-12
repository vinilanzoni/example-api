package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.ViaCepDto;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ViaCepService viaCepService;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository, ViaCepService viaCepService) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.viaCepService = viaCepService;
    }

    public Address create(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        ViaCepDto data;
        try {
            data = viaCepService.getAddressByCep(address.getCep());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP not found");
        }

        address.setCustomer(customer);
        address.setStreet(data.getLogradouro());
        address.setNeighborhood(data.getBairro());
        address.setCity(data.getLocalidade());
        address.setState(data.getUf());

        return addressRepository.save(address);
    }

    public List<Address> findAllByCustomer(Customer customer) {
        return addressRepository.findAllByCustomer(customer.getId());
    }

    public List<Address> findAllByCity(String city) {
        return addressRepository.findAllByCityLikeIgnoreCase(city);
    }

    public List<Address> findAllByState(String state) {
        return addressRepository.findAllByStateLikeIgnoreCase(state);
    }
}
