package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Category;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeClientDto {

    private Long id;
    private Integer idEntreprise;

    @JsonIgnore
    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    public LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if(ligneCommandeClient == null){
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto){
        if(ligneCommandeClientDto == null){
            return null;
        }
        LigneCommandeClient ligneCommandeClient= new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());


        return ligneCommandeClient;}
}
