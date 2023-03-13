package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.VideoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<VideoDetails, UUID> {
}
