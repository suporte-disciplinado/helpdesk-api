package com.suportedisciplinado.api.security;

import com.suportedisciplinado.api.model.Role;
import com.suportedisciplinado.api.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomUserDetailsTest {

    @SuppressWarnings("unchecked")
	@Test
    public void testUserDetailsMethods() {
        User dummyUser = new User();
        dummyUser.setId(1L);
        dummyUser.setName("John Doe");
        dummyUser.setEmail("john@example.com");
        dummyUser.setPassword("hashedPassword");
        dummyUser.setRole(Role.USER);
        dummyUser.setCreatedAt(LocalDateTime.now());

        UserDetails userDetails = new CustomUserDetails(dummyUser);

        assertEquals("john@example.com", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.getFirst().getAuthority());

        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }
}
