package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.ArticleRepository;
import com.projetmodule.gestiondestock.Repository.ClientRepository;
import com.projetmodule.gestiondestock.Repository.CommandeClientRepository;
import com.projetmodule.gestiondestock.Repository.LigneCommandeClientRepository;
import com.projetmodule.gestiondestock.dto.CommandeClientDto;
import com.projetmodule.gestiondestock.dto.LigneCommandeClientDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Client;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;
import com.projetmodule.gestiondestock.services.CommandeClientService;
import com.projetmodule.gestiondestock.validator.CommandeClientValidator;
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

public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientrepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    @Autowired

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientrepository,
                                     ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientrepository = clientrepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Commande client n'est pa valid");
            throw new InvalidEntityException("la commande client n'est valid", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);

        }
        Optional<Client> client = clientrepository.findById(dto.getClient().getId());
        if(client.isPresent()){
            log.warn("Client with Id { } was not found in the DB" , dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client ave l'id"+dto.getClient().getId()+"n'a ete trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND);

        }
        List<String> articleErrors =new ArrayList<>();
        if(dto.getLigneCommandeClient() != null){
            dto.getLigneCommandeClient().forEach(ligCMClt ->{
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
        CommandeClient savedCmdClt =commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if(dto.getLigneCommandeClient() != null){dto.getLigneCommandeClient().forEach(ligCmdClt -> {
            LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
            ligneCommandeClient.setCommandeClient(savedCmdClt);
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });}
       return  CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Long id) {
        if(id == null){
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Commande client n'est pas trouve avec l'ID"+id,
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }


    @Override
    public CommandeClientDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Commande client code is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Commande client n'est pas trouve avec le code"+code,
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Commande client ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);

    }
}
