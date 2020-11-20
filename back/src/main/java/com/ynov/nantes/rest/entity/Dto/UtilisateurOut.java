package com.ynov.nantes.rest.entity.Dto;

import lombok.Data;

@Data
public class UtilisateurOut {

    private int id;
    private String email;
    private String pseudo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
