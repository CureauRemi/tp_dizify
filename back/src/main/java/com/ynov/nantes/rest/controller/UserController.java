package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.User;
import com.ynov.nantes.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @PostMapping("/sign-in")
    public String login(@Param("login") String email, @Param("password") String password) {

        return null;
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
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
