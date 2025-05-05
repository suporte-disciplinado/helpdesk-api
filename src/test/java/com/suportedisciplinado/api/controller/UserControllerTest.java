//package com.suportedisciplinado.api.controller;
//
//import com.suportedisciplinado.api.model.User;
//import com.suportedisciplinado.api.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService service;
//
//    @Test
//    @WithMockUser(username = "john@example.com", roles = {"USER"})
//    void shouldReturnAllUsers() throws Exception {
//        when(service.findAll()).thenReturn(List.of(new User()));
//
//        mockMvc.perform(get("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}

package com.suportedisciplinado.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.suportedisciplinado.api.controller.UserController;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.suportedisciplinado.api.config.SecurityConfig;
import com.suportedisciplinado.api.security.CustomAuthenticationEntryPoint;
import com.suportedisciplinado.api.security.CustomUserDetailsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Nested
//    @Import(SecurityConfig.class)
//    class IntegrationTests {
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @MockitoBean
//        private CustomUserDetailsService customUserDetailsService;
//
//        @MockitoBean
//        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//
//        @MockBean
//        private UserService userService;
//
//        @MockBean
//        private UserRepository userRepository;
//
//        @MockBean
//        private PasswordEncoder passwordEncoder;
//
//        @Test
//        @WithMockUser(username = "john@example.com", roles = {"USER"})
//        public void testCurrentUser_Success() throws Exception {
//            mockMvc.perform(get("/me"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("john@example.com"));
//        }
//    }

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
    }

}
