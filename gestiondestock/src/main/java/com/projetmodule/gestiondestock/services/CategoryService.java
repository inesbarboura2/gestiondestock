package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    CategoryDto save(CategoryDto dto);
    CategoryDto findById(Long id);
//    CategoryDto findByCode(String code);
    List<CategoryDto> findAll();
    void delete(Long id);
}
