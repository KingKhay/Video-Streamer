package com.khaydev.videostream.app.utils;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import org.springframework.stereotype.Service;

@Service
public class EntityObjectMapperImpl implements EntityObjectMapper{
    @Override
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
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
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return user;
    }

    @Override
    public VideoDTO convertVideoDetailsToVideoDTO(VideoDetails videoDetails) {
        return new VideoDTO(
                videoDetails.getVideoId(),
                videoDetails.getVideoName(),
                videoDetails.getResourceUrl()
        );
    }
}
