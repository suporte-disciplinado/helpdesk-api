package com.suportedisciplinado.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john@example.com", roles = {"USER"})
    public void testCurrentUser_Success() throws Exception {
        mockMvc.perform(get("/me"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("john@example.com"));
    }

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
