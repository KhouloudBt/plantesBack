package com.soa.plantes.models;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Utilisation implements Serializable {
    @Id
    @GeneratedValue
    private long code;
    private String titre;
    private String guide;
    @ManyToMany
    private Collection<Produit> produits;

    public Utilisation() {
    }

    public Utilisation(String titre, String guide, Collection<Produit> produits) {
        this.titre = titre;
        this.guide = guide;
        this.produits = produits;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }
}
