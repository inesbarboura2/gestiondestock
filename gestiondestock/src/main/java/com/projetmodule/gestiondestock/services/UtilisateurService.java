package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.FournisseurDto;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Long id);

    List<UtilisateurDto> findAll();
    void delete(Long id);

    UtilisateurDto findByEmail(String email);
}
