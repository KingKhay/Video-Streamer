package com.khaydev.videostream.app.service.auth;

import com.khaydev.videostream.app.dto.LoginDTO;
import com.khaydev.videostream.app.dto.LoginResponse;
import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.model.User;

public interface AuthService {

    RegisterResponse register(User user);


    LoginResponse login(LoginDTO loginDTO);
}
