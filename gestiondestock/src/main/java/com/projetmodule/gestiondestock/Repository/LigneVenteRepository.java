package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository< LigneVente ,Long> {
}
