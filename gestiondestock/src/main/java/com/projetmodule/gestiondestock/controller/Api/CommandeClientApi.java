package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.CommandeClientDto;
import com.projetmodule.gestiondestock.services.CommandeClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {


    @PostMapping(APP_ROOT+"/commandesclients/create")
    ResponseEntity<CommandeClientDto> save( @RequestBody CommandeClientDto dto);
    @GetMapping(APP_ROOT+"/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Long id);
    @GetMapping(APP_ROOT+"/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto>findByCode(@PathVariable("codeCommandeClient") String code);
    @GetMapping(APP_ROOT+"/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
    @DeleteMapping(APP_ROOT+"/commandesclients/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Long id);
}
