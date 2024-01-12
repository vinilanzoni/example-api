package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

    @Override
    List<Customer> findAll();

    List<Customer> findAllByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndGenderContainingIgnoreCaseOrderByNameAsc(String name, String email, String gender, Pageable page);

    default List<Customer> findAllByParameters(String name, String email, String gender, Pageable page) {
        return findAllByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndGenderContainingIgnoreCaseOrderByNameAsc(name, email, gender, page);
    }

    @Query("SELECT c FROM Customer c INNER JOIN Address a ON a.customer = c " +
            "WHERE UPPER(c.name) LIKE CONCAT('%', UPPER(:name) ,'%') " +
            "AND UPPER(c.email) LIKE CONCAT('%', UPPER(:email) ,'%') " +
            "AND UPPER(c.gender) LIKE CONCAT('%', UPPER(:gender) ,'%') " +
            "AND UPPER(a.city) LIKE CONCAT('%', UPPER(:city) ,'%') " +
            "AND UPPER(a.state) LIKE CONCAT('%', UPPER(:state) ,'%') " +
            "ORDER BY c.name ASC")
    List<Customer> findAllWithAddressByParameters(
            @Param("name") String name,
            @Param("email") String email,
            @Param("gender") String gender,
            @Param("city") String city,
            @Param("state") String state,
            Pageable page
    );


}
