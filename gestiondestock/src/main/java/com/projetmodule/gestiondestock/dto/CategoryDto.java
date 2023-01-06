package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Category;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CategoryDto {

    private Long id;


    private String code;


    private String designation;


    private List<ArticleDto> articles;

    private Integer idEntreprise;

    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .idEntreprise(category.getIdEntreprise())
                .designation(category.getDesignation())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setIdEntreprise(categoryDto.getIdEntreprise());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());

        return category;}
}
