package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {


    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto findById(Long id);
    CommandeClientDto findByCode(String codeArticle);
    List<CommandeClientDto> findAll();
    void delete(Long id);
}
