package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.EntrepriseRepository;
import com.projetmodule.gestiondestock.Repository.UtilisateurRepository;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Entreprise;
import com.projetmodule.gestiondestock.models.Utilisateur;
import com.projetmodule.gestiondestock.services.UtilisateurService;
import com.projetmodule.gestiondestock.validator.EntrepriseValidator;
import com.projetmodule.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class UtilisateurServiceImpl implements UtilisateurService {
    public UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImpl(
            UtilisateurRepository utilisateurRepository
    ){
        this.utilisateurRepository=utilisateurRepository;
    }
    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Utilisateur is not valid{}" , dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valid", ErrorCodes.UTILISATEUR_NOT_VALID,errors);
        }

        return UtilisateurDto.fromEntity(utilisateurRepository.save(
                UtilisateurDto.toEntity(dto)
        ));
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if(id == null){
            log.error("Utlisateur ID is null");
            return  null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Entreprise avec l'Id="+id+"n'a ete trouver dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        return  utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException
                        ("Aucun utilisateur avec l'email"+email+
                                "n'ete trouve dans la BDD" ,
                                ErrorCodes.UTILISATEUR_NOT_FOUND));
    }
}
