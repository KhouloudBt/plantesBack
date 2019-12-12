package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Admins")
public class Admin implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password ;

    public Admin() {
    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
