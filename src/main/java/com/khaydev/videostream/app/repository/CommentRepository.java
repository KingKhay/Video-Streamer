package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query(value = "SELECT * FROM comment WHERE video_id = ?1", nativeQuery = true)
    List<Comment> findCommentsByVideoId(UUID id);
}
