package com.ynov.nantes.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Utilisateur {

    public Utilisateur(String pseudo, String email, String password, String avatar) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public Utilisateur() { }

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + password + "]";
    }

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String pseudo;

    private String email;

    private String password;

    private String avatar;

    @OneToOne(targetEntity = Favorite.class)
    private Favorite favorite;

    // -------------------------------------------------
    //                  GETTERS AND SETTERS
    // -------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
