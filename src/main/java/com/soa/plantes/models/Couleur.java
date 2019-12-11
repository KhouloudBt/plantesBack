package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Couleur implements Serializable {

    @Id
    @GeneratedValue
    private long id_c;
    private String nom;
    @ManyToMany
    private Collection<Produit>produits;

    public Couleur() {
    }

    public Couleur(String nom, Collection<Produit> produits) {
        this.nom = nom;
        this.produits = produits;
    }

    public long getId_c() {
        return id_c;
    }

    public void setId_c(long id_c) {
        this.id_c = id_c;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }


}
