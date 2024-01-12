package com.example.api.service;

import com.example.api.dto.ViaCepDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private final String url = "https://viacep.com.br/ws/";
    private final String format = "/json/";

    RestTemplate restTemplate = new RestTemplate();

    public ViaCepDto getAddressByCep(String cep) throws Exception {
        String fullUrl = url + cep + format;
        ViaCepDto data = restTemplate.getForObject(fullUrl, ViaCepDto.class);
        if (data.getErro() != null && data.getErro().equals(true)) {
            throw new Exception("CEP not found");
        } else {
            return data;
        }
    }
}
