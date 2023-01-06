package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);
    EntrepriseDto findById(Long id);

    List<EntrepriseDto> findAll();
    void delete(Long id);
}
