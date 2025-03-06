package com.suportedisciplinado.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
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

    @Nested
    @Import(SecurityConfig.class)
    static class IntegrationTests {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private CustomUserDetailsService customUserDetailsService;

        @MockitoBean
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Test
        @WithMockUser(username = "john@example.com", roles = {"USER"})
        public void testCurrentUser_Success() throws Exception {
            mockMvc.perform(get("/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john@example.com"));
        }
    }

    @Nested
    static class UnitTests {

        @Test
        public void testCurrentUser_Unauthenticated_Direct() {
            UserController controller = new UserController();
            ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
                controller.currentUser(null);
            });
            assertEquals(401, ex.getStatusCode().value());
            assertEquals("User not authenticated", ex.getReason());
        }
    }
}
