package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;
public interface ClientApi {
    @PostMapping(value =APP_ROOT+ "/client/create" ,
            consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT+"/client/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Long id);
    @GetMapping(value = APP_ROOT+"/client/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();


    @DeleteMapping(value= APP_ROOT+"/client/delete/{idClient}")
    void delete(@PathVariable("idClient") Long id);
}
