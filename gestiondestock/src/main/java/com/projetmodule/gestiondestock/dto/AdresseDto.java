package com.projetmodule.gestiondestock.dto;

import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDto {

    private String adresse1;


    private String adresse2;


    private String ville;


    private String codePostal;


    private String pays;
    public AdresseDto fromEntity(Adresse adresse){
        if(adresse == null){
            return null;
        }
        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .pays(adresse.getPays())
                .build();
    }

    public Adresse toEntity(AdresseDto adresseDto){
        if(adresseDto == null){
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());
        return adresse;}
    //        return Adresse.builder()
//                .adresse1(adresseDto.getAdresse1())
//                .adresse2(adresseDto.getAdresse2())
//                .ville(adresseDto.getVille())
//                .codePostal(adresseDto.getCodePostal())
//                .pays(adresseDto.getPays())
//                .build();}
}
