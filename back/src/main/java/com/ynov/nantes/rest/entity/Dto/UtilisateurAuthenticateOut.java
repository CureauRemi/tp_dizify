package com.ynov.nantes.rest.entity.Dto;

import lombok.Data;

@Data
public class UtilisateurAuthenticateOut {

        private int id;
        private String email;
        private String token;
        private String pseudo;

        public UtilisateurAuthenticateOut(String email, String token, String pseudo) {
            this.email = email;
            this.token = token;
            this.pseudo = pseudo;
        }

}
