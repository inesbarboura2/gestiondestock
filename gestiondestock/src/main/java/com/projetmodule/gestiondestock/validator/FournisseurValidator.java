package com.projetmodule.gestiondestock.validator;

import com.projetmodule.gestiondestock.dto.ClientDto;
import com.projetmodule.gestiondestock.dto.FournisseurDto;
import com.projetmodule.gestiondestock.models.Fournisseur;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors = new ArrayList<>();

        if(fournisseurDto == null){
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner l'email du fournisseur");
            errors.add("Veuillez renseigner le num du telephone du fournisseur");
            return errors;
        }

        if( !StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if(  !StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }
        if(  !StringUtils.hasLength(fournisseurDto.getEmail())){
            errors.add("Veuillez renseigner l'email du fournisseur");
        }
        if(  !StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Veuillez renseigner le num du telephone du fournisseur");
        }


        return errors;
    }
}
