package com.khaydev.videostream.app.utils.mapper;

import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.Comment;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;

public interface EntityObjectMapper {

    UserDTO convertUserToUserDTO(User user);
    VideoDTO convertVideoDetailsToVideoDTO(VideoDetails videoDetails);

    CommentDTO convertCommentToCommentDTO(Comment comment);

}
