package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository< LigneCommandeClient ,Long> {
}
