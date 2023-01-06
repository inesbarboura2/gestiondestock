package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.ArticleRepository;
import com.projetmodule.gestiondestock.Repository.CategoryRepository;
import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Category;
import com.projetmodule.gestiondestock.services.CategoryService;
import com.projetmodule.gestiondestock.validator.ArticleValidator;
import com.projetmodule.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    public CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Category is not valid{}" , dto);
            throw new InvalidEntityException("La Category n'est pas valid", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }

        return CategoryDto.fromEntity(categoryRepository.save(
                CategoryDto.toEntity(dto)
        ));
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id == null){
            log.error("Category ID is null");
            return  null;
        }

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune category avec l'ID"+id+"n'ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

//    @Override
//    public CategoryDto findByCode(String code) {
//        if(StringUtils.hasLength(code)){
//            log.error("Category CODE is null");
//            return  null;
//        }
//        return categoryRepository.findCategoryByCodeCategory(code)
//                        .map(CategoryDto::fromEntity)
//                .orElseThrow(()->new EntityNotFoundException(
//                        "Aucune category avec le code "+code+"n'ete trouve dans la BDD"
//                )
//        );
//    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);


    }


}
