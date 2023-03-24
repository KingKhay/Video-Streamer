package com.khaydev.videostream.app.service.user;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO findUserById(UUID id);


    UserDTO updateUser(UserDTO user, UUID id);

    UserDTO deleteUser(UUID id);

    List<VideoDTO> findVideos(UUID id);

    List<UserDTO> searchUsersByUsername(String username);
}