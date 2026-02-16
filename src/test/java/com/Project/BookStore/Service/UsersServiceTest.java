package com.Project.BookStore.Service;

import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Repository.UsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest  {

    @Mock
    private UsersRepo repo;
    @Mock
    private BCryptPasswordEncoder encoder;
    @InjectMocks
    private UsersService service;
    @Mock
    Users user;

    @Test
    void test_RegiseteredUserDuplicateEntry(){

        user.setUsername("ravi singh");
        when(repo.existsByUsername("ravi singh")).thenReturn(true);
        ResponseEntity<String> response = service.registerUser(user);
        assertNotNull(response, "Response should not be null even if user exist");

    }

    @Test
    void test_RegisterUser_DatabaseCrash_ThrowException(){

        when(repo.existsByUsername(anyString())).thenReturn(false);
        when(repo.save(any())).thenThrow(new RuntimeException("DB connection fail"));
        ResponseEntity<String> response = service.registerUser(user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertFalse(Boolean.parseBoolean(response.getBody()));
    }

}