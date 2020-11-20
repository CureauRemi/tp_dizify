package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --------------
    // REQUETES
    // --------------

    @ResponseBody
    @GetMapping("{id}")
    public Utilisateur getUserById(final @PathVariable("id") String userId) {
        try {
            Optional<Utilisateur> artist = userRepository.findById(Integer.valueOf(userId));
            return artist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @PutMapping
    public Utilisateur editUser(@RequestBody Utilisateur user) {
        Utilisateur updated = userRepository.save(user);
        return updated;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public HttpStatus deleteUser(@Param("id") Integer id) {
        try{
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
