package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/me")
    public UserDetails currentUser(@AuthenticationPrincipal UserDetails user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        return user;
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty())
            return userService.getAllUsers();

        return userService.getByNameOrEmailUsers(search);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/api/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @PutMapping("/api/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}

