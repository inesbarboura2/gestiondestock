package com.projetmodule.gestiondestock.services.auth;

import com.projetmodule.gestiondestock.Repository.UtilisateurRepository;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.models.Utilisateur;
import com.projetmodule.gestiondestock.models.auth.ExtendedUser;
import com.projetmodule.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurService service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur=service.findByEmail(email);
               List<SimpleGrantedAuthority> authorities = new ArrayList<>();
               utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
               return new ExtendedUser(utilisateur.getEmail() , utilisateur.getMotDePasse() , utilisateur.getEntreprise().getId(),authorities);
    }
}
