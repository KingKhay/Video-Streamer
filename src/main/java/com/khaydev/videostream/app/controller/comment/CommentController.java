package com.khaydev.videostream.app.controller.comment;

import com.khaydev.videostream.app.dto.CommentDTO;
import com.khaydev.videostream.app.service.comment.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public List<CommentDTO> findCommentsByVideo(@PathVariable UUID id){
        return service.findCommentByVideo(id);
    }
}
