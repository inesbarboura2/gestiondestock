package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
    @PostMapping(value =APP_ROOT+ "/utilisateur/create" ,
            consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT+"/utilisateur/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Long id);
    @GetMapping(value = APP_ROOT+"/utilisateur/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();


    @DeleteMapping(value= APP_ROOT+"/utilisateurdelete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Long id);
}
