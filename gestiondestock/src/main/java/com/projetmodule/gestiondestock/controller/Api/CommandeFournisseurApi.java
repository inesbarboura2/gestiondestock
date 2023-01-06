package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeFournisseurApi {
    @PostMapping(APP_ROOT +"/commandefournisseur/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);


    @GetMapping(APP_ROOT+"/commandesfournisseur/{idCommandeFournisseur}")
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Long id);

    @GetMapping(APP_ROOT+"/commandesfournisseur/{codeCommandeFournisseur}")
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String codeArticle);

    @GetMapping(APP_ROOT+"/commandesfournisseur/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(APP_ROOT+"/commandesfournisseur/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Long id);
}
