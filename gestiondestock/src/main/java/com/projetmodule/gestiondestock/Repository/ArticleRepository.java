package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ArticleRepository extends JpaRepository< Article , Long> {
    Optional<Article> findArticleByCodeArticle(String codeArticle);



}
