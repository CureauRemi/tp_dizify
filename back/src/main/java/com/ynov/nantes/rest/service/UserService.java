package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Utilisateur loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
