package com.projetmodule.gestiondestock.dto;



import com.projetmodule.gestiondestock.models.Role;
import com.projetmodule.gestiondestock.models.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private Long id;


    private String code;

    private Instant dateVente;

    private Integer idEntreprise;

    private String commantaire;

    private List<LigneVenteDto> ligneVentes;
    public static VentesDto fromEntity(Ventes ventes){
        if(ventes == null){
            return null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .idEntreprise(ventes.getIdEntreprise())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commantaire(ventes.getCommantaire())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto){
        if(ventesDto == null){
            return null;
        }
        Ventes ventes= new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());
        ventes.setCode(ventesDto.getCode());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCommantaire(ventesDto.getCommantaire());
        return ventes;}
}
