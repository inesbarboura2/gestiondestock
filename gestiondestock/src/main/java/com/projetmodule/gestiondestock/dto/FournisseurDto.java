package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.CommandeFournisseur;
import com.projetmodule.gestiondestock.models.Entreprise;
import com.projetmodule.gestiondestock.models.Fournisseur;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;
@Data
@Builder
public class FournisseurDto {

    private Long id;


    private String nom;
    private Integer idEntreprise;


    private String prenom;

    @Embedded
    private AdresseDto adresse;


    private String image;


    private String email;


    private String numTel;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null){
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .image(fournisseur.getImage())
                .email(fournisseur.getEmail())
                .idEntreprise(fournisseur.getIdEntreprise())
                .numTel(fournisseur.getNumTel())
                .build();
    }
    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if(fournisseurDto == null){
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setImage(fournisseurDto.getImage());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());


        return fournisseur;
    }
}
