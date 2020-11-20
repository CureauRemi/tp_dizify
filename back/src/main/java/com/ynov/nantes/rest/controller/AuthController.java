package com.ynov.nantes.rest.controller;


import com.ynov.nantes.rest.entity.Dto.AuthenticationIn;
import com.ynov.nantes.rest.entity.Dto.UtilisateurAuthenticateOut;
import com.ynov.nantes.rest.entity.Dto.UtilisateurIn;
import com.ynov.nantes.rest.entity.Dto.UtilisateurOut;
import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.entity.mapper.UtilisateurMapper;
import com.ynov.nantes.rest.jwt.ConfJWT;
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
        private final AuthenticationManager authenticationManager;
        @Autowired
        private final ServiceFindUserEmail serviceFindUserEmail;
        @Autowired
        private final ConfJWT jwtTokenUtil;
        @Autowired
        private final UserRepository utilisateurRepository;
        @Autowired
        private UtilisateurMapper utilisateurMapper;
        @Autowired
        private ServiceFindUserEmailAuthenticate serviceFindUserEmailAuthenticate;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(AuthenticationManager authenticationManager, ServiceFindUserEmail serviceFindUserEmail, ConfJWT jwtTokenUtil, UserRepository utilisateurRepository, UtilisateurMapper utilisateurMapper, ServiceFindUserEmailAuthenticate serviceFindUserEmailAuthenticate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.serviceFindUserEmail = serviceFindUserEmail;
        this.jwtTokenUtil = jwtTokenUtil;
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.serviceFindUserEmailAuthenticate = serviceFindUserEmailAuthenticate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


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


        final UserDetails userDetails = serviceFindUserEmailAuthenticate.loadUserByUsername(authentificationIn.getEmail());
        UserDetails userConnected = serviceFindUserEmail.loadUserByEmail(authentificationIn.getEmail());

        UtilisateurAuthenticateOut response = new UtilisateurAuthenticateOut(userConnected.getUsername(),jwtTokenUtil.generateToken(userDetails), userConnected.getPassword());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UtilisateurIn utilisateurIn) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(utilisateurIn.getEmail());
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setEmail(utilisateurIn.getEmail());
            utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateurIn.getPassword()));
            utilisateur.setPseudo(utilisateurIn.getPseudo());
            utilisateurRepository.save(utilisateur);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(utilisateurMapper.map(utilisateur, UtilisateurOut.class));
        } else {
            return ResponseEntity.status(403)
                    .body("Username already use");
        }
    }
}
