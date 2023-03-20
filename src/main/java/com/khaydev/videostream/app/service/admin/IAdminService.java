package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class IAdminService implements AdminService{

    private final RoleRepository repository;

    public IAdminService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role createRole(Role role) {
        return repository.save(role);
    }
}
