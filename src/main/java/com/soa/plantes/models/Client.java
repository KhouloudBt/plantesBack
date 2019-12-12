package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name="Clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue
    private long  id;

    private String nom;
    private String prénom;
    private String mail;
    private String login;
    private Boolean type;
    private String password;
    private double remise;
    private String profile;
    private double coeffFidelite;

    @OneToOne
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private Collection <Commande> commandes;

    public Client() {
    }

    public Client(String nom, String prénom,double coeffFidelite, String mail, String login, String password,  String profile, Adresse adresse, Collection<Commande> commandes, Boolean type) {
        this.nom = nom;
        this.prénom = prénom;
        this.coeffFidelite = coeffFidelite;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.remise = 0;
        this.profile = profile;
        this.adresse = adresse;
        this.commandes = commandes;
        this.type =false;
    }

    public Client(String nom, String prénom, String mail) {
        this.nom = nom;
        this.prénom = prénom;
        this.mail = mail;
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

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }


    public double getCoeffFidelite() {
        return coeffFidelite;
    }

    public void setCoeffFidelite(double coeffFidelite) {
        this.coeffFidelite = coeffFidelite;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }
}
