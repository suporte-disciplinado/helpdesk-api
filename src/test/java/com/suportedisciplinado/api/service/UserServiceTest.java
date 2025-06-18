package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAllUsersResponseEntity() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<List<User>> result = userService.getAllUsers();
        assertEquals(2, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void shouldReturnUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Optional<User>> result = userService.getUserById(1L);
        assertTrue(result.getBody().isPresent());
        assertEquals(1L, result.getBody().get().getId());
    }

    @Test
    void shouldCreateUser() {
        User user = new User();
        user.setName("John");
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

        ResponseEntity<User> result = userService.createUser(user);
        assertEquals("John", result.getBody().getName());
    }

    @Test
    void shouldUpdateExistingUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Updated");
        user.setEmail("updated@example.com");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old");
        existingUser.setEmail("old@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

        ResponseEntity<User> result = userService.updateUser(user);
        assertEquals("Updated", result.getBody().getName());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistentUser() {
        User user = new User();
        user.setId(999L);
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<User> result = userService.updateUser(user);
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void shouldDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        ResponseEntity<String> result = userService.deleteUser(1L);
        assertEquals("User deleted successfully!", result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void shouldSearchByNameOrEmail() {
        List<User> users = Arrays.asList(new User());
        when(userRepository.findByNameOrEmail("search"))
                .thenReturn(users);

        ResponseEntity<List<User>> result = userService.getByNameOrEmailUsers("search");
        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }
}
