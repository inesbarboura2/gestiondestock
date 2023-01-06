package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;
import com.projetmodule.gestiondestock.models.Role;
import com.projetmodule.gestiondestock.models.Utilisateur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Long id;


    private  String roleName;
    private Integer idEntreprise;


    private UtilisateurDto utilisateur;
    public RoleDto fromEntity(Role role){
        if(role == null){
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .utilisateur(UtilisateurDto.fromEntity(role.getUtilisateur()))
                .idEntreprise(role.getIdEntreprise())
                .roleName(role.getRoleName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if(roleDto == null){
            return null;
        }
        Role role= new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());


        return role;}
}
