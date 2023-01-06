package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.*;
import com.projetmodule.gestiondestock.dto.CommandeClientDto;
import com.projetmodule.gestiondestock.dto.CommandeFournisseurDto;
import com.projetmodule.gestiondestock.dto.LigneCommandeClientDto;
import com.projetmodule.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.*;
import com.projetmodule.gestiondestock.services.CommandeFournisseurService;
import com.projetmodule.gestiondestock.validator.CommandeClientValidator;
import com.projetmodule.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class CommandeFournisseurServiceIml implements CommandeFournisseurService {
    private final FournisseurRepository clientrepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurrepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    @Autowired
    public CommandeFournisseurServiceIml(CommandeFournisseurRepository commandeFournisseurRepository,
                                         FournisseurRepository clientrepository,
                                         ArticleRepository articleRepository,
                                         LigneCommandeFournisseurRepository ligneCommandeClientRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.clientrepository = clientrepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Commande Fournisseur n'est pa valid");
            throw new InvalidEntityException("la commande Fournisseur n'est valid", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);

        }
        Optional<Fournisseur> fournisseur = clientrepository.findById(dto.getFournisseur().getId());
        if(fournisseur.isPresent()){
            log.warn("Fournisseur with Id { } was not found in the DB" , dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur ave l'id"+dto.getFournisseur().getId()+"n'a ete trouve dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND);

        }
        List<String> articleErrors =new ArrayList<>();
        if(dto.getLigneCommandeFornisseurs() != null){
            dto.getLigneCommandeFornisseurs().forEach(ligCMClt ->{
                if(ligCMClt.getArticle() != null){
                    Optional<Article> article=articleRepository.findById(ligCMClt.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID"+ligCMClt.getArticle().getId() +"n'existe pas");

                    } else{
                        articleErrors.add("Impossible d'enregistre une commande avec un article null");

                    }
                }
            });

        }
        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeFournisseur savedCmdClt =commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        if(dto.getLigneCommandeFornisseurs() != null){dto.getLigneCommandeFornisseurs().forEach(ligCmdClt -> {
            LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdClt);
            ligneCommandeFournisseur.setCommandeFournisseur(savedCmdClt);
            ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        });}
        return  CommandeFournisseurDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        if(id == null){
            log.error("Commande fouenisseur ID is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Commande Fournisseur n'est pas trouve avec l'ID"+id,
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Commande Fournisseur code is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Commande fournisseur n'est pas trouve avec le code"+code,
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Commande client ID is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);

    }
}
