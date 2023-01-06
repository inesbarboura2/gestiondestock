package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.UtilisateurApi;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import com.projetmodule.gestiondestock.services.FournisseurService;
import com.projetmodule.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService=utilisateurService;
    }
    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }
}
