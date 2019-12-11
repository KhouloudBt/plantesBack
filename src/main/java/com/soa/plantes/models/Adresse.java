package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Adresse  implements Serializable {
    @Id
    @GeneratedValue
    private   Long id ;
    private String city;
    private int streetNumber ;
    private String postalCode;

    @OneToOne(mappedBy = "adresse")
    private Client client;

    public Adresse() {
    }

    public Adresse(String city, int streetNumber, String postalCode, Client client) {
        this.city = city;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
