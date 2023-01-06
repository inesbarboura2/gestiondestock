package com.projetmodule.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Category;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Long id;


    private String codeArticle;


    private String designation;


    private BigDecimal prixUnitaireHT;


    private BigDecimal tauxTva;


    private BigDecimal prixUnitaireTtc;


    private String photo;


    private CategoryDto category;

    private Integer idEntreprise;
    public static ArticleDto fromEntity(Article article){
        if(article == null){
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHT(article.getPrixUnitaireHT())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .idEntreprise(article.getIdEntreprise())
                .photo(article.getPhoto())
                .build();
    }

    public static Article toEntity(ArticleDto articleDto){
        if(articleDto == null){
            return null;
        }
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHT(articleDto.getPrixUnitaireHT());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        return article;}

}
