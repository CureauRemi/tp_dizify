package com.ynov.nantes.rest.controller;


import com.ynov.nantes.rest.entity.dto.auth.AuthenticationIn;
import com.ynov.nantes.rest.entity.dto.auth.UtilisateurAuthenticateOut;
import com.ynov.nantes.rest.entity.dto.utilisateur.UtilisateurIn;
import com.ynov.nantes.rest.entity.dto.utilisateur.UtilisateurOut;
import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.entity.mapper.UtilisateurMapper;
import com.ynov.nantes.rest.jwt.ConfJWT;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.UserRepository;
import com.ynov.nantes.rest.service.ServiceFindUserEmail;
import com.ynov.nantes.rest.service.ServiceFindUserEmailAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {


        @Autowired
        private  AuthenticationManager authenticationManager;
        @Autowired
        private  ServiceFindUserEmail serviceFindUserEmail;
        @Autowired
        private  ConfJWT jwtTokenUtil;
        @Autowired
        private  UserRepository utilisateurRepository;
        @Autowired
        private UtilisateurMapper utilisateurMapper;
        @Autowired
        private ServiceFindUserEmailAuthenticate serviceFindUserEmailAuthenticate;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;
        @Autowired
        private FavoriteRepository favoriteRepository;

    // -----------------------
    // REQUÊTE DE CONNECTION
    // -----------------------

    @PostMapping("/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationIn authentificationIn) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationIn.getEmail(), authentificationIn.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        Utilisateur utilisateurCourant = utilisateurRepository.findByEmail(authentificationIn.getEmail());
        final UserDetails userDetails = serviceFindUserEmailAuthenticate.loadUserByUsername(authentificationIn.getEmail());
        UserDetails userConnected = serviceFindUserEmail.loadUserByEmail(authentificationIn.getEmail());

        UtilisateurAuthenticateOut response = new UtilisateurAuthenticateOut(utilisateurCourant.getId(), userConnected.getUsername(),jwtTokenUtil.generateToken(userDetails), utilisateurCourant.getPseudo());


        return ResponseEntity.ok(response);
    }
    // -----------------------
    // REQUÊTE D'INSCRIPTION
    // -----------------------
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UtilisateurIn utilisateurIn) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(utilisateurIn.getEmail());
        if (utilisateur == null) {
            Utilisateur newUtilisateur = new Utilisateur();
            newUtilisateur.setEmail(utilisateurIn.getEmail());
            newUtilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateurIn.getPassword()));
            newUtilisateur.setPseudo(utilisateurIn.getPseudo());
            Utilisateur currentUser = utilisateurRepository.save(newUtilisateur);
            Favorite fav = new Favorite();
            fav.setUser(currentUser);
            favoriteRepository.save(fav);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(utilisateurMapper.map(newUtilisateur, UtilisateurOut.class));
        } else {
            return ResponseEntity.status(403)
                    .body("Username already use");
        }
    }
}
