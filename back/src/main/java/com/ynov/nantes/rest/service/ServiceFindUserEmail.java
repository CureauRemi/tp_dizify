package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Dto.UtilisateurOut;
import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.entity.mapper.UtilisateurMapper;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ServiceFindUserEmail {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UtilisateurMapper utilisateurMapper;


    public UtilisateurOut loadUserByUsername(String email) {
        Utilisateur user = userRepository.findByEmail(email);
        return Utilisateur.map(userRepository.findByEmail(email), UtilisateurOut.class);
    }

}
