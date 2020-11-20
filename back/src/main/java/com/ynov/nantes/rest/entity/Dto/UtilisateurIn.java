package com.ynov.nantes.rest.entity.Dto;

import lombok.Data;

@Data
public class UtilisateurIn {
    private String email;
    private String password;
    private String pseudo;

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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
