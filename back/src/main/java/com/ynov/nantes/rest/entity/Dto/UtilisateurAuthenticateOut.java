package com.ynov.nantes.rest.entity.Dto;

import lombok.Data;

@Data
public class UtilisateurAuthenticateOut {

        private int id;
        private String username;
        private String token;

        public UtilisateurAuthenticateOut(int id, String username, String token) {
            this.id = id;
            this.username = username;
            this.token = token;
        }

}
