package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.FournisseurApi;
import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.dto.FournisseurDto;
import com.projetmodule.gestiondestock.services.EntrepriseService;
import com.projetmodule.gestiondestock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FournisseurContoller implements FournisseurApi {
    private FournisseurService fournisseurService;
    @Autowired
    public FournisseurContoller(FournisseurService fournisseurService) {
        this.fournisseurService=fournisseurService;
    }
    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Long id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Long id) {
        fournisseurService.delete(id);

    }
}
