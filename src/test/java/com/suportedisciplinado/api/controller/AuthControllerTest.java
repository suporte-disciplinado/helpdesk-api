package com.suportedisciplinado.api.controller;

import com.suportedisciplinado.api.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import com.suportedisciplinado.api.security.CustomUserDetailsService;
import com.suportedisciplinado.api.security.CustomAuthenticationEntryPoint;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, CustomAuthenticationEntryPoint.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;
    
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void registerNewUser_Success() throws Exception {
        User user = new User(null, "John Doe", "john@example.com", "secret", null, LocalDateTime.now());
        
        Mockito.when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("secret")).thenReturn("hashedSecret");
        Mockito.when(userRepository.save(Mockito.any(User.class)))
               .thenAnswer(invocation -> invocation.getArgument(0));
        
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(content().string("User registered successfully"));
    }
    
    @Test
    public void registerExistingUser_Failure() throws Exception {
        User user = new User(null, "John Doe", "john@example.com", "secret", null, LocalDateTime.now());
        
        Mockito.when(userRepository.findByEmail("john@example.com"))
               .thenReturn(Optional.of(user));
        
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Email already exists"));
    }
}
