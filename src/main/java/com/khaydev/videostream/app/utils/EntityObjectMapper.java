package com.khaydev.videostream.app.utils;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.model.User;

public interface EntityObjectMapper {

    public UserDTO convertUserToUserDTO(User user);

    public User convertUserDTOToUser(UserDTO userDTO);
}
