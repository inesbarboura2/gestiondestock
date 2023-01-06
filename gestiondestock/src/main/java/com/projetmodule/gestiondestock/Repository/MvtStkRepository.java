package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository< MvtStk ,Long > {
}
