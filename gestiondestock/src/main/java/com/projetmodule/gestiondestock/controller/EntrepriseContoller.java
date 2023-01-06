package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.EntrepriseApi;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import com.projetmodule.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntrepriseContoller implements EntrepriseApi {
    private EntrepriseService entrepriseService;
    @Autowired
    public EntrepriseContoller(EntrepriseService entrepriseService) {
        this.entrepriseService=entrepriseService;
    }
    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Long id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Long id) {
        entrepriseService.delete(id);

    }
}
