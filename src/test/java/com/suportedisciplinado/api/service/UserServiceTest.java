package com.suportedisciplinado.api.service;

import com.suportedisciplinado.api.model.User;
import com.suportedisciplinado.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void shouldReturnAllUsers() {
        User user = new User();
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(user);
    }
}
