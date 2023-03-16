package com.khaydev.videostream.app.controller.video;

import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.Comment;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.repository.VideoRepository;
import com.khaydev.videostream.app.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VideoDTO saveVideo(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @PathVariable UUID id){

        return videoService.uploadVideo(file, name, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<VideoDetails> findAll(){
       return videoService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public VideoDTO getVideo(@PathVariable UUID id){
        return videoService.getVideo(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public void addComment(@PathVariable UUID id, @RequestBody Comment comment){
        videoService.addComment(id, comment);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/comments")
    public List<CommentDTO> getAllComments(@PathVariable UUID id){
        return videoService.findAllComments(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/field/{field}")
    public List<VideoDTO> getVideosSortedByField(@PathVariable String field){
        return videoService.findVideosWithSorting(field);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public List<VideoDTO> getVideosWithPagination(@RequestParam ("offset") int pageNumber,
                                                  @RequestParam ("limit") int numberOfRecordsPerPage){
        return videoService.findVideosWithPagination(pageNumber, numberOfRecordsPerPage);

    }
}
