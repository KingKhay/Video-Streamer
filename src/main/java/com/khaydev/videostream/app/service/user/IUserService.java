package com.khaydev.videostream.app.service.user;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.utils.mapper.EntityObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IUserService implements  UserService{
    private final UserRepository repository;
    private final EntityObjectMapper objectMapper;



    @Override
    public UserDTO findUserById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return objectMapper.convertUserToUserDTO(user);
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

    public List<UserDTO> searchUsersByUsername(String username){

        List<User> users = repository.findUsersByUsernameContainingIgnoreCase(username);
        List<UserDTO> userDTOS = users.stream()
                .map(objectMapper::convertUserToUserDTO)
                .toList();

        return userDTOS;
    }
}
