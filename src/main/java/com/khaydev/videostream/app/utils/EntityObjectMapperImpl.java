package com.khaydev.videostream.app.utils;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.model.User;
import org.springframework.stereotype.Service;

@Service
public class EntityObjectMapperImpl implements EntityObjectMapper{
    @Override
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setDob(user.getDob());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;

    }

    @Override
    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setDob(userDTO.getDob());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return user;
    }
}
