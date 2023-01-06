package com.projetmodule.gestiondestock.services.impl;

import com.projetmodule.gestiondestock.Repository.EntrepriseRepository;
import com.projetmodule.gestiondestock.Repository.RoleRepository;
import com.projetmodule.gestiondestock.dto.EntrepriseDto;
import com.projetmodule.gestiondestock.dto.RoleDto;
import com.projetmodule.gestiondestock.dto.UtilisateurDto;
import com.projetmodule.gestiondestock.exception.EntityNotFoundException;
import com.projetmodule.gestiondestock.exception.ErrorCodes;
import com.projetmodule.gestiondestock.exception.InvalidEntityException;
import com.projetmodule.gestiondestock.models.Entreprise;
import com.projetmodule.gestiondestock.services.EntrepriseService;
import com.projetmodule.gestiondestock.services.UtilisateurService;
import com.projetmodule.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    public EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RoleRepository roleRepository;
    @Autowired
    public EntrepriseServiceImpl(
            EntrepriseRepository entrepriseRepository,
            UtilisateurService utilisateurService, RoleRepository roleRepository){
        this.entrepriseRepository=entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.roleRepository = roleRepository;
    }
    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Entreprise is not valid{}" , dto);
            throw new InvalidEntityException("L'entreprise n'est pas valid", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }

        EntrepriseDto savedEntreprise =EntrepriseDto.fromEntity(entrepriseRepository.save(
                EntrepriseDto.toEntity(dto)
        ));
        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);
        UtilisateurDto savedUser = utilisateurService.save(utilisateur);
        RoleDto rolesDto = RoleDto.builder()
                .roleName("Admin").utilisateur(savedUser).build();

        roleRepository.save(RoleDto.toEntity(rolesDto));
        return  savedEntreprise;

    }
    private UtilisateurDto fromEntreprise(EntrepriseDto dto){
        return  UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getName())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }
  private String generateRandomPassword(){
        return  "ahhfhshfjfhkdhfjk";
  }
    @Override
    public EntrepriseDto findById(Long id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return  null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        EntrepriseDto dto = EntrepriseDto.fromEntity(entreprise.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Entreprise avec l'Id="+id+"n'a ete trouver dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }



    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }


}
