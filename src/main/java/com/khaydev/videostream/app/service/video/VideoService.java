package com.khaydev.videostream.app.service.video;

import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.VideoDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface VideoService {

    VideoDTO uploadVideo(MultipartFile file, VideoDetails videoDetails, UUID id);

    VideoDTO downloadVideo(UUID id);

    VideoDTO getVideo(UUID id);

    List<VideoDetails> findAll();
}
