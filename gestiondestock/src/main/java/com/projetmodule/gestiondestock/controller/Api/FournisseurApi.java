package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface FournisseurApi {
    @PostMapping(value =APP_ROOT+ "/fournisseur/create" ,
            consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT+"/fournisseur/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Long id);
    @GetMapping(value = APP_ROOT+"/fournisseur/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();


    @DeleteMapping(value= APP_ROOT+"/fournisseur/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Long id);
}
