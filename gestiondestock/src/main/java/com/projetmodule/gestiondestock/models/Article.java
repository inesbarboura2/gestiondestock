package com.projetmodule.gestiondestock.models;




import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table()
public class Article extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column()
    private String codeArticle;

    @Column
    private String designation;

    @Column
    private BigDecimal prixUnitaireHT;

    @Column
    private BigDecimal tauxTva;

    @Column
    private BigDecimal prixUnitaireTtc;

    @Column
    private String photo;

    @Column
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @ManyToMany(mappedBy = "article")
    private List<LigneVente> ligneVente;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MvtStk> mvtstks;
}
