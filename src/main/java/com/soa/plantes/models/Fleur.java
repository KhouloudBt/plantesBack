package com.soa.plantes.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
@DiscriminatorValue("F")
public class Fleur extends Produit{

    private String odeur;
    @OneToOne(mappedBy = "fleur")
    private Plante plante;

    public Fleur() {
        super();
    }

    public Fleur(String nom, double prix, Long stock, Collection<Couleur> couleurs, Collection<Utilisation> utilisations, Collection<Commande> commandes, String odeur, Plante plante) {
        super(nom, prix, stock, couleurs, utilisations, commandes);
        this.odeur = odeur;
        this.plante=plante;
    }


    public String getOdeur() {
        return odeur;
    }

    public void setOdeur(String odeur) {
        this.odeur = odeur;
    }

    public Plante getPlante() {
        return plante;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
}
