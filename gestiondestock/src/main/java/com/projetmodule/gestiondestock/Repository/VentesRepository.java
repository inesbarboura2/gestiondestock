package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes ,Long> {
    Optional<Ventes> findVentesByCode(String code);
}
