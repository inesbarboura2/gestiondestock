package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.*;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class UtilisateurDto {

    private Long id;



    private String nom;


    private String prenom;


    private String email;

    private Instant dateDeNaissance;

    private AdresseDto adresse;


    private String photo;

    private String motDePasse;


    private EntrepriseDto entreprise;


    private List<RoleDto> roles;
    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .motDePasse(utilisateur.getMotDePasse())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .photo(utilisateur.getPhoto())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise())).build();

    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if(utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());

        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        return utilisateur;
    }


}
