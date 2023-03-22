package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class IAdminService implements AdminService{

    private final RoleRepository repository;

    public IAdminService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return repository.save(role);
    }
}
