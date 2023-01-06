package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClientService {


    ClientDto save(ClientDto dto);

    ClientDto findById(Long id);
    List<ClientDto> findAll();
    void delete(Long id);
}
