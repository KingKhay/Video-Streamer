package com.khaydev.videostream.app.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaydev.videostream.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class IUserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private IUserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
}