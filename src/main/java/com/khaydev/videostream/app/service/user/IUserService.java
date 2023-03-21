package com.khaydev.videostream.app.service.user;

import com.khaydev.videostream.app.dto.RegisterResponse;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.RoleRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.utils.jwt.JwtUtils;
import com.khaydev.videostream.app.utils.mapper.EntityObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IUserService implements  UserService{

    private final JwtUtils jwtUtils;

    private final String roleUser = "ROLE_USER";

    private final UserRepository repository;
    private final EntityObjectMapper objectMapper;

    private final RoleRepository roleRepository;

    @Override
    public RegisterResponse save(User user) {
        Role userRole = roleRepository.getRoleByName(roleUser);
        user.getRoles().add(userRole);

        repository.save(user);

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName())
                .toList();

        String token = jwtUtils.createJwt(user.getUsername(),roles);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .jwtToken(token)
                .build();
    }

    @Override
    public UserDTO findUserById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return objectMapper.convertUserToUserDTO(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(objectMapper::convertUserToUserDTO)
                .toList();
    }

    @Override
    public UserDTO updateUser(UserDTO userDetails, UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setDob(userDetails.getDob());

        repository.save(user);

        return userDetails;
    }

    @Override
    public UserDTO deleteUser(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        repository.deleteById(id);
        return objectMapper.convertUserToUserDTO(user);
    }

    @Override
    public List<VideoDTO> findVideos(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return user.getVideosUploaded()
                .stream()
                .map(objectMapper::convertVideoDetailsToVideoDTO)
                .toList();
    }

    public UserDTO findUserByUsername(String username){
        User user = repository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return objectMapper.convertUserToUserDTO(user);
    }
}
