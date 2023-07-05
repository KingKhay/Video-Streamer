package com.khaydev.videostream.app.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.PasswordResetToken;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.PasswordTokenRepository;
import com.khaydev.videostream.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IUserService implements  UserService{
    private final UserRepository repository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ObjectMapper mapper;


    @Override
    public UserDTO findUserById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return mapper.convertValue(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDetails, UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());

        repository.save(user);

        return mapper.convertValue(user, UserDTO.class);
    }



    @Override
    public List<VideoDTO> findVideos(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return user.getVideosUploaded()
                .stream()
                .map(video -> mapper.convertValue(video, VideoDTO.class))
                .toList();
    }

    @Override
    public List<UserDTO> searchUsersByUsername(String username){

        List<User> users = repository.findUsersByUsernameContainingIgnoreCase(username);
        return users.stream()
                .map(user -> mapper.convertValue(user, UserDTO.class))
                .toList();
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }
}
