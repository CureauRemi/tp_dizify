package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.User;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUserById(final @PathVariable("id") String userId) {
        try {
            Optional<User> artist = userRepository.findById(Integer.valueOf(userId));
            return artist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User addArtist(@RequestBody User user) {
        User saved = userRepository.save(user);
        return saved;
    }

    @ResponseBody
    @PutMapping("/user/{id}")
    public User editUser(@RequestBody User user) {
        User updated = userRepository.save(user);
        return updated;
    }

    @DeleteMapping("/user")
    public String deleteUser(@RequestBody User user) {
        try{
            userRepository.delete(user);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "User deleted successfully !";
    }
}
