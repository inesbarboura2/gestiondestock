package com.projetmodule.gestiondestock.validator;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto entrepriseDto){
        List<String> errors = new ArrayList<>();
        if (entrepriseDto == null){
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner l'adresse de l'entreprise");
            errors.add("Veuillez renseigner le code fiscal de l'entreprise ");
            errors.add("Veuillez renseigner l'email de l'entreprise ");
            errors.add("Veuillez renseigner le num telephone de l'entreprise ");
            errors.add("Veuillez renseigner site web de l'entreprise");
            return errors;
        }
        if (!StringUtils.hasLength(entrepriseDto.getName())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())){
            errors.add("Veuillez renseigner le numero telephone de l'entreprise");
        }
        if (entrepriseDto.getAdresse() == null){
            errors.add("Veuillez renseigner l'adresse de l'entreprise ");
        }
        if (entrepriseDto.getCodeFiscal() == null){
            errors.add("Veuillez renseigner le code fiscal de l'entreprise ");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getSteWeb())){
            errors.add("Veuillez renseigner le site web de l'entreprise");

        }
        return errors;
}}
