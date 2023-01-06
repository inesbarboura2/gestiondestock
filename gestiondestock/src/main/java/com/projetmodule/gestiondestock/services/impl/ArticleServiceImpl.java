package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.ArticleRepository;
import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.services.ArticleService;
import com.projetmodule.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    public ArticleRepository articleRepository;
    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository
    ){
        this.articleRepository=articleRepository;
    }
    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid{}" , dto);
            throw new InvalidEntityException("L'article n'est pa valid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        return ArticleDto.fromEntity(articleRepository.save(
                ArticleDto.toEntity(dto)
        ));
    }

    @Override
    public ArticleDto findById(Long id) {
        if(id == null){
            log.error("Article ID is null");
            return  null;
        }
        Optional<Article> article = articleRepository.findById(id);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'Id="+id+"n'a ete trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if(StringUtils.hasLength(codeArticle)){
            log.error("Article CODE is null");
            return  null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le code="+codeArticle+"n'a ete trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);


    }
}
