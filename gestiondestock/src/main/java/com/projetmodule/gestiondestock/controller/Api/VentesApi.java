package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.VentesDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface VentesApi {
    @PostMapping(APP_ROOT+"/ventes/create")
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(APP_ROOT+"/ventes/{idVentes}")
    VentesDto findById(@PathVariable("idVentes") Long id);


    @GetMapping(APP_ROOT+"/ventes/{codeVentes}")
    VentesDto findByCode(@PathVariable("codeVentes") String code);

    @GetMapping(APP_ROOT+"/ventes/all")
    List<VentesDto> findAll();

    @DeleteMapping(APP_ROOT+"/ventes/delete/{idVentes}")
    void delete(@PathVariable("idVentes") Long id);
}