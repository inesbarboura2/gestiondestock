package com.projetmodule.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetmodule.gestiondestock.models.Adresse;
import com.projetmodule.gestiondestock.models.Category;
import com.projetmodule.gestiondestock.models.Client;
import com.projetmodule.gestiondestock.models.CommandeClient;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Long id;

    private String nom;


    private String prenom;


    private AdresseDto adresse;

    private String photo;


    private String mail;
    private Integer idEntreprise;


    private String numTel;


    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){
        if(client == null){
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .photo(client.getPhoto())
                .idEntreprise(client.getIdEntreprise())
                .mail(client.getMail())
                .numTel(client.getNumTel()).build();
    }

    public static Client toEntity(ClientDto clientDto){
        if(clientDto == null){
            return null;
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        client.setNumTel(clientDto.getNumTel());
        client.setPrenom(clientDto.getPrenom());
        client.setMail(clientDto.getMail());
        client.setPhoto(clientDto.getPhoto());
        return client;
    }


}
