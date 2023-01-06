package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.EntrepriseRepository;
import com.projetmodule.gestiondestock.Repository.FournisseurRepository;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import com.projetmodule.gestiondestock.dto.FournisseurDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Entreprise;
import com.projetmodule.gestiondestock.models.Fournisseur;
import com.projetmodule.gestiondestock.services.FournisseurService;
import com.projetmodule.gestiondestock.validator.EntrepriseValidator;
import com.projetmodule.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class FournisseurServiceImpl implements FournisseurService {
    public FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(
            FournisseurRepository fournisseurRepository
    ){
        this.fournisseurRepository=fournisseurRepository;
    }
    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Fournisseur is not valid{}" , dto);
            throw new InvalidEntityException("Fournisseur n'est pas valid", ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }

        return FournisseurDto.fromEntity(fournisseurRepository.save(
                FournisseurDto.toEntity(dto)
        ));
    }

    @Override
    public FournisseurDto findById(Long id) {
        if(id == null){
            log.error("Fournisseur ID is null");
            return  null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        FournisseurDto dto = FournisseurDto.fromEntity(fournisseur.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Fournisseur avec l'Id="+id+"n'a ete trouver dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Fournisseur ID is null");
            return;
        }
        fournisseurRepository.deleteById(id);

    }
}
