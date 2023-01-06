package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.CategoryApi;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import com.projetmodule.gestiondestock.models.Category;
import com.projetmodule.gestiondestock.services.ArticleService;
import com.projetmodule.gestiondestock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryController implements CategoryApi {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService=categoryService;
    }
    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

//    @Override
//    public CategoryDto findByCode(String codeCategory) {
//        return categoryService.findByCode(codeCategory);
//    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }
}
