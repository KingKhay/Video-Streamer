package com.khaydev.videostream.app.service.user;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    public UserDTO save(User user);

    public User findUserById(UUID id);

    public List<User> findAllUsers();

    public UserDTO updateUser(UserDTO user, UUID id);

    public UserDTO deletUser(UUID id);
}