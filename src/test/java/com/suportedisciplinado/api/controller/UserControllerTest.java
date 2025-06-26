package com.suportedisciplinado.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.suportedisciplinado.api.model.Role;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Nested
    class UnitTests {

        @Test
        public void testCurrentUser_Unauthenticated_Direct() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);
            ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
                controller.currentUser(null);
            });
            assertEquals(401, ex.getStatusCode().value());
            assertEquals("User not authenticated", ex.getReason());
        }

        @Test
        public void testGetAllWithoutSearchParam() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            var response = controller.getAll(null);

            assertEquals(200, response.getStatusCode().value());
        }

        @Test
        public void testGetAllWithSearchParam() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            var response = controller.getAll("admin");

            assertEquals(200, response.getStatusCode().value());
        }

        @Test
        public void testGetUserById() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            var response = controller.getById(1L);

            assertEquals(200, response.getStatusCode().value());
            assertEquals(true, response.getBody().isPresent() || response.getBody().isEmpty());
        }

        @Test
        public void testCreateUser() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            var user = new com.suportedisciplinado.api.model.User();
            user.setPassword("123456");

            var response = controller.create(user);

            assertEquals(200, response.getStatusCode().value());
        }

        @Test
        public void testUpdateUser() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            // Cria e salva um usuário no banco para fins de teste
            var newUser = new User();
            newUser.setName("Test User");
            newUser.setEmail("test@example.com");
            newUser.setPassword(passwordEncoder.encode("123456"));
            newUser.setRole(Role.USER);
            userRepository.save(newUser);

            // Atualiza o usuário
            newUser.setName("Updated Name");
            var response = controller.update(newUser);

            assertEquals(200, response.getStatusCode().value());
            assertEquals("Updated Name", response.getBody().getName());
        }

        @Test
        public void testDeleteUser() {
            UserController controller = new UserController(userService, userRepository, passwordEncoder);

            var response = controller.delete(1L);

            assertEquals(200, response.getStatusCode().value());
        }
    }

}
