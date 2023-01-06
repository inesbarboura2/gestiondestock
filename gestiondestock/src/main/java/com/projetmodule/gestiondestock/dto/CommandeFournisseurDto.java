package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeFournisseurDto {

    private Long id;


    private String code;


    private Instant dateCommande;


    private FournisseurDto fournisseur;
    private Integer idEntreprise;

    private List<LigneCommandeFournisseurDto> ligneCommandeFornisseurs;
    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur == null){
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }
    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto){
        if(commandeFournisseurDto == null){
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());

        return commandeFournisseur;
    }

}
