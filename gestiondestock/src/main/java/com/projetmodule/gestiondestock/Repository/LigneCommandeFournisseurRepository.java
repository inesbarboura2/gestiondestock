package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeFournisseurRepository extends JpaRepository< LigneCommandeFournisseur,Long> {
}
