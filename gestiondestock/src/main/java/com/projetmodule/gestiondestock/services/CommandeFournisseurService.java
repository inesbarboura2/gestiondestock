package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.CommandeClientDto;
import com.projetmodule.gestiondestock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {


    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto findById(Long id);
    CommandeFournisseurDto findByCode(String codeArticle);
    List<CommandeFournisseurDto> findAll();
    void delete(Long id);
}
