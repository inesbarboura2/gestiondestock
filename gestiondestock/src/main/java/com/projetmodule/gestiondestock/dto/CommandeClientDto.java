package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Client;
import com.projetmodule.gestiondestock.models.CommandeClient;
import com.projetmodule.gestiondestock.models.LigneCommandeClient;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeClientDto {

    private Long id;


    private String code;


    private Instant dateCommande;


    private ClientDto client;
    private Integer idEntreprise;


    private List<LigneCommandeClientDto> ligneCommandeClient;
    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEntreprise())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .build();
    }
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){
        if(commandeClientDto == null){
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        return commandeClient;
    }
}
