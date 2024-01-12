package com.example.api.dto;

import com.example.api.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
public class AddressDto {
    @NotNull
    @NotBlank
    private String cep;

    @NotNull
    @PositiveOrZero
    private Integer number;


    private String complement;

    public Address toEntity() {
        return new Address(cep, number, complement);
    }

}
