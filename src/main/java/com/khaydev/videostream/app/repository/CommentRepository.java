package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
