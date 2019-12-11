package com.soa.plantes.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@DiscriminatorValue("P")
public class Plante extends Produit {

    private String origine;
    private String description;

    @OneToOne
    private Fleur fleur;

    public Plante() {
        super();
    }

    public Fleur getFleur() {
        return fleur;
    }

    public void setFleur(Fleur fleur) {
        this.fleur = fleur;
    }

    public Plante(String nom, double prix, Long stock, Collection<Couleur> couleurs, Collection<Utilisation> utilisations, Collection<Commande> commandes, String origine, String description, Fleur fleur) {
        super(nom, prix, stock, couleurs, utilisations, commandes);
        this.origine = origine;
        this.description = description;
        this.fleur=fleur;
    }
    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
