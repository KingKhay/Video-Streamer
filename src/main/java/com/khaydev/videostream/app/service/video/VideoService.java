package com.khaydev.videostream.app.service.video;

import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.Comment;
import com.khaydev.videostream.app.model.VideoDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface VideoService {

    VideoDTO uploadVideo(MultipartFile file, String videoName, UUID id);

    VideoDTO getVideo(UUID id);

    List<VideoDetails> findAll();

    void addComment(UUID id, Comment comment);

    List<CommentDTO> findAllComments(UUID id);

    List<VideoDTO> findVideosWithSorting(String fieldToSortBy);

    List<VideoDTO> findVideosWithPagination(int pageNumber, int numberOfRecordsPerPage);
}
