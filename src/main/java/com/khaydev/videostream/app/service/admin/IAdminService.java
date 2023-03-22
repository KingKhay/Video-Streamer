package com.khaydev.videostream.app.service.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IAdminService implements AdminService{

    private final RoleRepository repository;

    private final UserRepository userRepository;

    private final String ADMIN_ROLE = "ROLE_ADMIN";

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return repository.save(role);
    }

    @Override
    public void addAdmin(UUID id) {
       User user = userRepository.findById(id)
               .orElseThrow(UserNotFoundException::new);
        Role roleByName = repository.findRoleByName(ADMIN_ROLE);

        user.getRoles().add(roleByName);
        userRepository.save(user);
    }
}
