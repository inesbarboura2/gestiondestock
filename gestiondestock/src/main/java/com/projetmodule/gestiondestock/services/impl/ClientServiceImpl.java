package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.ClientRepository;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Category;
import com.projetmodule.gestiondestock.models.Client;
import com.projetmodule.gestiondestock.services.ClientService;
import com.projetmodule.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    public ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(
            ClientRepository clientRepository
    ){
        this.clientRepository=clientRepository;
    }
    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Client is not valid{}" , dto);
            throw new InvalidEntityException("Le Client n'est pas valid", ErrorCodes.CLIENT_NOT_VALID,errors);
        }

        return ClientDto.fromEntity(clientRepository.save(
                ClientDto.toEntity(dto)
        ));
    }

    @Override
    public ClientDto findById(Long id) {
        if(id == null){
            log.error("Client ID is null");
            return  null;
        }
        Optional<Client> client = clientRepository.findById(id);
        ClientDto dto = ClientDto.fromEntity(client.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune client avec l'Id="+id+"n'a ete trouver dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Article ID is null");
            return;
        }
        clientRepository.deleteById(id);

    }
}
