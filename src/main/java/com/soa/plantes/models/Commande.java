package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue
    private long id_cf;

    private Date date;
    private long quantite;
    private Boolean valide;
    private double sommeAchat;
    @ManyToOne
    private Client client;
    @ManyToMany
    private Collection<Produit> produits;

    public Commande() {
    }

    public Commande(Date date, long quantite, Boolean valide, Client client, Collection<Produit> produits, double sommeAchat ) {
        this.date = date;
        this.quantite = quantite;
        this.valide = valide;
        this.client = client;
        this.produits = produits;
        this.sommeAchat=sommeAchat;
    }

    public long getId_cf() {
        return id_cf;
    }

    public void setId_cf(long id_cf) {
        this.id_cf = id_cf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public double getSommeAchat() {
        return sommeAchat;
    }

    public void setSommeAchat(double sommeAchat) {
        this.sommeAchat = sommeAchat;
    }
}
