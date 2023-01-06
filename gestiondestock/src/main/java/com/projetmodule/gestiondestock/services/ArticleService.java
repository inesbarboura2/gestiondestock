package com.projetmodule.gestiondestock.services;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ArticleService {
     ArticleDto save(ArticleDto dto);
     ArticleDto findById(Long id);
     ArticleDto findByCodeArticle(String codeArticle);
     List<ArticleDto> findAll();
     void delete(Long id);
}
