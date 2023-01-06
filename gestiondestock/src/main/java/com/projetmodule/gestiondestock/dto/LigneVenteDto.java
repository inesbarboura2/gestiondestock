package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.LigneCommandeFournisseur;
import com.projetmodule.gestiondestock.models.LigneVente;
import com.projetmodule.gestiondestock.models.Ventes;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneVenteDto {

    private Long id;
    private Integer idEntreprise;
    @JsonIgnore
    private VentesDto vente;

    @JsonIgnore
    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;
    public LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if(ligneVenteDto == null){
            return null;
        }
        LigneVente ligneVente= new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());


        return ligneVente;}
}
