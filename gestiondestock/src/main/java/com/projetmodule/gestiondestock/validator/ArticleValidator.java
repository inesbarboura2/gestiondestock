package com.projetmodule.gestiondestock.validator;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();
        if (articleDto == null){
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner le designation de l'article");
            errors.add("Veuillez renseigner le prix unitaire HtT de l'article ");
            errors.add("Veuillez renseigner le Taux TVA de l'article ");
            errors.add("Veuillez renseigner le Prix Unitaire ttc de l'article ");
            errors.add("Veuillez renseigner la category de l'article");
            return errors;
        }
        if (!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veuillez renseigner le code  de l'article");
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuillez renseigner le designation de l'article");
        }
        if (articleDto.getPrixUnitaireHT() == null){
            errors.add("Veuillez renseigner le prix unitaire HtT de l'article ");
        }
        if (articleDto.getTauxTva() == null){
            errors.add("Veuillez renseigner le Taux TVA de l'article ");
        }
        if(articleDto.getPrixUnitaireTtc() == null){
            errors.add("Veuillez renseigner le Prix Unitaire ttc de l'article ");
        }
        if (articleDto.getCategory() == null){
            errors.add("Veuillez renseigner la category de l'article");
        }

        return errors;
    }
}
