package com.ynov.nantes.rest.entity.dto.utilisateur;

import com.ynov.nantes.rest.entity.Utilisateur;

public class UtilisateurDto {

    private Integer id;

    private String pseudo;

    private String email;

    private String avatar;

    public UtilisateurDto(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.pseudo = utilisateur.getPseudo();
        this.email = utilisateur.getEmail();
        this.avatar = utilisateur.getAvatar();
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
