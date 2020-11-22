package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.entity.mapper.UtilisateurMapper;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ServiceFindUserEmail {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UtilisateurMapper utilisateurMapper;


    public UserDetails loadUserByEmail(String email) {
        Utilisateur user = userRepository.findByEmail(email);
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return userDetails;
    }

}
