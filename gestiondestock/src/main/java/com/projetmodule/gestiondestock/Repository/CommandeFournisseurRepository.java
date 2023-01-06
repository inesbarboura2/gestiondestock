package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository< CommandeFournisseur ,Long > {
    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
