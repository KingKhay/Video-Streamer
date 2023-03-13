package com.khaydev.videostream.app.service;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public UserDTO save(User user);

    public User findUserById(UUID id);

    public List<User> findAllUsers();

    public User updateUser(User user, UUID id);

    public User deletUser(UUID id);
}