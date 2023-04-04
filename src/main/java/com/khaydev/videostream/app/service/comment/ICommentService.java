package com.khaydev.videostream.app.service.comment;

import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.repository.CommentRepository;
import com.khaydev.videostream.app.utils.mapper.EntityObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ICommentService implements CommentService{

    private final CommentRepository commentRepository;

    private final EntityObjectMapper objectMapper;

    public ICommentService(CommentRepository commentRepository, EntityObjectMapper objectMapper) {
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CommentDTO> findCommentsByVideo(UUID id) {
        return commentRepository.findCommentsByVideoId(id)
                .stream()
                .map(objectMapper::convertCommentToCommentDTO)
                .toList();
    }


}
