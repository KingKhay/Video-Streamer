package com.khaydev.videostream.app.controller.video;

import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/videos")
public class VideoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public String saveVideo(VideoDetails video){

        System.out.println(video);

        UUID id = UUID.fromString("46842306-3c63-4da9-9209-352b9db5e91e");
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        video.setUser(user);
        repository.save(video);


        user.getVideosUploaded().add(video);
        userRepository.save(user);
        return "Video saved for user " + id.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public String findAll(){
       List<VideoDetails> videos = repository.findAll();

       videos.forEach(System.out::println);
       return "All the videos";
    }
}
