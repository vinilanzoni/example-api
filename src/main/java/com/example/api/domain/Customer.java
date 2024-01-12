package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @NotEmpty
    @Email
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @Getter
    @Setter
    private String gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Getter
    @Setter
    @JsonProperty("addresses")
    private List<Address> addressList;

    public Customer(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
