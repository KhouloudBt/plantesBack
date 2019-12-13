package com.soa.plantes.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_pr",discriminatorType=DiscriminatorType.STRING,length=1)
public abstract class Produit implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String nom;
    private double prix;
    private Long stock;
    @ManyToMany
    private Collection <Couleur> couleurs;
    @ManyToMany
    private Collection<Utilisation> utilisations;
    @ManyToMany
    private Collection<Commande> commandes;

    public Produit()
    {}

    public Produit(String nom, double prix, Long stock, Collection<Couleur> couleurs, Collection<Utilisation> utilisations, Collection<Commande> commandes) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.couleurs = couleurs;
        this.utilisations = utilisations;
        this.commandes = commandes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if (prix <0 )
        System.out.println("valeur erronée ");
        else {
        this.prix = prix;}
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        if (stock <0 )
            System.out.println("valeur erronée ");
        else {
        this.stock = stock;}
    }

    public Collection<Couleur> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(Collection<Couleur> couleurs) {
        this.couleurs = couleurs;
    }

    public Collection<Utilisation> getUtilisations() {
        return utilisations;
    }

    public void setUtilisations(Collection<Utilisation> utilisations) {
        this.utilisations = utilisations;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }
}
