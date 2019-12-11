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
    private String  type;
    private double coeffFidelite;
    private String mail;
    private String login;
    private String password;
    private double remise;
    private String profile;

    @OneToOne
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private Collection <Commande> commandes;

    public Client() {
    }

    public Client(String nom, String prénom, String type, double coeffFidelite, String mail, String login, String password, double remise, String profile, Adresse adresse, Collection<Commande> commandes) {
        this.nom = nom;
        this.prénom = prénom;
        this.type = type;
        this.coeffFidelite = coeffFidelite;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.remise = remise;
        this.profile = profile;
        this.adresse = adresse;
        this.commandes = commandes;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCoeffFidelite() {
        return coeffFidelite;
    }

    public void setCoeffFidelite(double coeffFidelite) {
        this.coeffFidelite = coeffFidelite;
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
