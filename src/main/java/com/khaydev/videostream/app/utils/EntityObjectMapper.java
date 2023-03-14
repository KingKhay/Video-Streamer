package com.khaydev.videostream.app.utils;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;

public interface EntityObjectMapper {

    UserDTO convertUserToUserDTO(User user);

    User convertUserDTOToUser(UserDTO userDTO);

    VideoDTO convertVideoDetailsToVideoDTO(VideoDetails videoDetails);

}
