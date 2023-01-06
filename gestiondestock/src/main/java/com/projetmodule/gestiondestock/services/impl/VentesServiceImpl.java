package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.ArticleRepository;
import com.projetmodule.gestiondestock.Repository.LigneVenteRepository;
import com.projetmodule.gestiondestock.Repository.VentesRepository;
import com.projetmodule.gestiondestock.dto.*;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.LigneVente;
import com.projetmodule.gestiondestock.models.Utilisateur;
import com.projetmodule.gestiondestock.models.Ventes;
import com.projetmodule.gestiondestock.services.VentesService;
import com.projetmodule.gestiondestock.validator.VentesValidator;
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
public class VentesServiceImpl implements VentesService {
    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Ventes n'est pas valid");
            throw new InvalidEntityException("l'objet vente n'est pas valid", ErrorCodes.VENTE_NOT_VALID , errors);
        }
        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if(article.isEmpty()){
                  articleErrors.add("aucun article avec l'id"+ligneVenteDto.getArticle().getId()+"n'a ete trouve dans la BDD");
            }
        });
        if(!articleErrors.isEmpty()){
            log.error("one or more articles were not found in the DB,{}",errors);
            throw new InvalidEntityException("un ou plusieur article n'ont pas ete trouve dans la bd", ErrorCodes.VENTE_NOT_VALID , errors);
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVentes(savedVentes);
            ligneVenteRepository.save(ligneVente);
            });
        return  VentesDto.fromEntity(savedVentes);
    }


    @Override
    public VentesDto findById(Long id) {
        if(id == null){
            log.error("Commande client ID is null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Ventes n'est pas trouve avec l'ID"+id,
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Vente code is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Ventes n'est pas trouve avec le code"+code,
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Vente ID is null");
            return;
        }
        ventesRepository.deleteById(id);


    }
}
