package com.khaydev.videostream.app.service.comment;

import com.khaydev.videostream.app.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ICommentService implements CommentService{

    private final CommentRepository commentRepository;

    public ICommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
