package com.projetmodule.gestiondestock.controller.Api;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {
    @PostMapping(value =APP_ROOT+ "/entreprise/create" ,
            consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT+"/entreprise/{idEntreprise}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Long id);

    @GetMapping(value = APP_ROOT+"/entreprise/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();


    @DeleteMapping(value= APP_ROOT+"/entreprise/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Long id);
}
