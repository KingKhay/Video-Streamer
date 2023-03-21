package com.khaydev.videostream.app.service.user;

import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    RegisterResponse save(User user);

    UserDTO findUserById(UUID id);

    List<UserDTO> findAllUsers();

    UserDTO updateUser(UserDTO user, UUID id);

    UserDTO deleteUser(UUID id);

    List<VideoDTO> findVideos(UUID id);

    UserDTO findUserByUsername(String username);
}