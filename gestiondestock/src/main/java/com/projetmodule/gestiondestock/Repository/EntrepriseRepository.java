package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Client;
import com.projetmodule.gestiondestock.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface EntrepriseRepository extends JpaRepository< Entreprise,Long> {

}
