package com.soa.plantes.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Admins")
public class Admin implements Serializable {

    @Id
    private Long login;
    private String password ;

    public Admin() {
    }

    public Admin(Long login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getLogin() {
        return login;
    }

    public void setLogin(Long login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
