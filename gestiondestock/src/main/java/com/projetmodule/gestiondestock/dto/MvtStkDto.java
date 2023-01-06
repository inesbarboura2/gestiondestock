package com.projetmodule.gestiondestock.dto;

import com.projetmodule.gestiondestock.models.Article;
import com.projetmodule.gestiondestock.models.LigneVente;
import com.projetmodule.gestiondestock.models.MvtStk;
import com.projetmodule.gestiondestock.models.TypeMvtStk;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
public class MvtStkDto {

    private Long id;


    private Instant dateMvt;


    private BigDecimal quantite;


    private TypeMvtStk typeMvt;
    private Integer idEntreprise;


    private ArticleDto article;
    public MvtStkDto fromEntity(MvtStk mvtStk){
        if(mvtStk == null){
            return null;
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .idEntreprise(mvtStk.getIdEntreprise())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .dateMvt(mvtStk.getDateMvt())
                .build();
    }

    public MvtStk toEntity(MvtStkDto mvtStkDto){
        if(mvtStkDto == null){
            return null;
        }
        MvtStk mvtStk= new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());



        return mvtStk;}


}
