package com.soa.plantes.models;

import javax.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue
    private   Long id ;
    private String city;
    private int streetNumber ;
    private String postalCode;
    private Client client;





}
