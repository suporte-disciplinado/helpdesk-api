package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<List<User>> getByNameOrEmailUsers(String search) {
        return ResponseEntity.ok(userRepository.findByNameOrEmail(search));
    }

    public ResponseEntity<Optional<User>> getUserById(Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userRepository.saveAndFlush(user));
    }

    public ResponseEntity<User> updateUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());
        if (findUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User userToUpdate = findUser.get();
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());

        User updatedUser = userRepository.saveAndFlush(userToUpdate);
        return ResponseEntity.ok(userRepository.saveAndFlush(updatedUser));
    }

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
