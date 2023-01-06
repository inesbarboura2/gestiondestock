package com.projetmodule.gestiondestock.Repository;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository< Role ,Long > {
}
