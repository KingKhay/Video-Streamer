package com.khaydev.videostream.app.service;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.utils.EntityObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IUserService implements  UserService{

    private final UserRepository repository;
    private final EntityObjectMapper objectMapper;

    @Autowired
    public IUserService(UserRepository repository, EntityObjectMapper mapper) {
        this.repository = repository;
        this.objectMapper = mapper;
    }

    @Override
    public UserDTO save(User user) {
        repository.save(user);

        return objectMapper.convertUserToUserDTO(user);
    }

    @Override
    public User findUserById(UUID id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User userDetails, UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return extractNewDetailsAndUpdateUser(user, userDetails);
    }

    @Override
    public User deletUser(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        repository.deleteById(id);
        return user;
    }

    public User extractNewDetailsAndUpdateUser(User oldDetails, User newDetails){
        oldDetails.setUsername(newDetails == null ? oldDetails.getUsername() : newDetails.getUsername());
        oldDetails.setLastName(newDetails == null ? oldDetails.getLastName() : newDetails.getLastName());
        oldDetails.setFirstName(newDetails == null ? oldDetails.getFirstName() : newDetails.getFirstName());
        oldDetails.setDob(newDetails == null ? oldDetails.getDob() : newDetails.getDob());

        return oldDetails;
    }
}
