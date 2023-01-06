package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import com.projetmodule.gestiondestock.dto.FournisseurDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface FournisseurService {
    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Long id);

    List<FournisseurDto> findAll();
    void delete(Long id);
}
