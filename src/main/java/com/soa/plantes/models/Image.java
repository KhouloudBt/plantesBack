package com.soa.plantes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte [] pic;
    @ManyToOne
    private Plante plante;

    public Image() {
    }

    public Image(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

    public Image(String name, String type, byte[] pic, Plante plante) {
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.plante = plante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Plante getPlante() {
        return plante;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
}
