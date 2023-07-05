package com.khaydev.videostream.app.service.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.repository.CommentRepository;
import com.khaydev.videostream.app.utils.mapper.EntityObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ICommentService implements CommentService{

    private final CommentRepository commentRepository;

    private final ObjectMapper mapper;

    @Override
    public List<CommentDTO> findCommentsByVideo(UUID id) {
        return commentRepository.findCommentsByVideoId(id)
                .stream()
                .map(comment -> mapper.convertValue(comment, CommentDTO.class))
                .toList();
    }


}
