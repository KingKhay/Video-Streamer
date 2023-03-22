package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.model.Role;

public interface AdminService {

    Role createRole(RoleDTO role);
}
