package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.ClientApi;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.services.CategoryService;
import com.projetmodule.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientController implements ClientApi {
    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService=clientService;
    }
    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientService.findById(id);
    }


    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }
}
