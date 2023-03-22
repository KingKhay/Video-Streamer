package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.model.Role;

import java.util.UUID;

public interface AdminService {

    Role createRole(RoleDTO role);

    void addAdmin(UUID id);
}
