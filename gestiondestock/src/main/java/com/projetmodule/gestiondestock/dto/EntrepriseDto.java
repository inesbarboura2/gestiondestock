package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.Entreprise;
import com.projetmodule.gestiondestock.models.Utilisateur;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class EntrepriseDto {

    private Long id;


    private  String name;


    private AdresseDto adresse;


    private String codeFiscal;


    private String photo;


    private String email;


    private String numTel;


    private String steWeb;


    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .name(entreprise.getName())
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())

                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .steWeb(entreprise.getSteWeb())
                .build();
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if(entrepriseDto == null){
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setName(entrepriseDto.getName());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setSteWeb(entrepriseDto.getSteWeb());

        return entreprise;
    }

}
