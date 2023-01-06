package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.CommandeClientApi;
import com.projetmodule.gestiondestock.dto.CommandeClientDto;
import com.projetmodule.gestiondestock.services.CommandeClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
         return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Long id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Long id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
