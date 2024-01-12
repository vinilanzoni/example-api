package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String cep;

    @Column(nullable = false)
    @Getter
    @Setter
    private String street;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer number;

    @Column(nullable = true)
    @Getter
    @Setter
    private String complement;

    @Column(nullable = false)
    @Getter
    @Setter
    private String neighborhood;

    @Column(nullable = false)
    @Getter
    @Setter
    private String city;

    @Column(nullable = false)
    @Getter
    @Setter
    private String state;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Getter
    @Setter
    @JsonIgnore
    private Customer customer;

    public Address(String cep, Integer number, String complement) {
        this.cep = cep;
        this.number = number;
        this.complement = complement;
    }


}
