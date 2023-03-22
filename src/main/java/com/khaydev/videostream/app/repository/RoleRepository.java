package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role getRoleByName(String name);

    Role findRoleByName(String name);
}
