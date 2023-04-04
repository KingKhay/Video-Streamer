package com.khaydev.videostream.app.service.comment;

import com.khaydev.videostream.app.dto.CommentDTO;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentDTO> findCommentsByVideo(UUID id);
}
