package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.CommandeFournisseur;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;
import com.projetmodule.gestiondestock.models.LigneCommandeFournisseur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Long id;
    private Integer idEntreprise;

    @JsonIgnore
    private ArticleDto article;


    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;
    public LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if(ligneCommandeFournisseur == null){
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto){
        if(ligneCommandeFournisseurDto == null){
            return null;
        }
        LigneCommandeFournisseur ligneCommandeFournisseur= new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());


        return ligneCommandeFournisseur;}

}
