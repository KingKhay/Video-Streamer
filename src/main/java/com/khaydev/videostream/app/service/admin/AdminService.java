package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    Role createRole(RoleDTO role);

    void addAdmin(UUID id);

    List<User> findAllUsers();

    User deleteUser(UUID id);
}
