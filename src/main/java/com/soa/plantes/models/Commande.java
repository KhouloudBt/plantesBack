package com.soa.plantes.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class Commande {

    @Id
    @GeneratedValue
    private long id_cf;
    private Date date;
    private long quantite;
    private Boolean valide;
    private Client client;
    private Produit produit;


}
