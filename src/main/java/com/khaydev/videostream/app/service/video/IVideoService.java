package com.khaydev.videostream.app.service.video;

import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.exception.video.VideoNotFoundException;
import com.khaydev.videostream.app.model.Comment;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import com.khaydev.videostream.app.repository.UserRepository;
import com.khaydev.videostream.app.repository.VideoRepository;
import com.khaydev.videostream.app.utils.EntityObjectMapper;
import com.khaydev.videostream.aws.service.AWSService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class IVideoService implements  VideoService {


    private final UserRepository userRepository;

    private final VideoRepository videoRepository;

    private final EntityObjectMapper objectMapper;

    private final AWSService awsService;


    public IVideoService(
                         UserRepository userRepository,
                         VideoRepository videoRepository,
                         EntityObjectMapper mapper,
                         AWSService service) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.objectMapper = mapper;
        this.awsService = service;
    }

    @Override
    public VideoDTO uploadVideo(MultipartFile file,
                                String videoName,
                                UUID id) {

        User user =  userRepository
                .findById(id).orElseThrow(UserNotFoundException::new);

        String resourceUrl = awsService.saveVideo(file);

        VideoDetails videoDetails = new VideoDetails();
        videoDetails.setResourceUrl(resourceUrl);
        videoDetails.setVideoName(videoName);

        user.addVideo(videoDetails);

        userRepository.save(user);
        return objectMapper.convertVideoDetailsToVideoDTO(videoDetails);
    }

    @Override
    public VideoDTO getVideo(UUID id) {
        VideoDetails videoDetails = videoRepository.findById(id)
                .orElseThrow(VideoNotFoundException::new);

        return objectMapper.convertVideoDetailsToVideoDTO(videoDetails);
    }

    @Override
    public List<VideoDetails> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public void addComment(UUID id, Comment comment) {
        VideoDetails videoDetails = videoRepository.findById(id)
                .orElseThrow(VideoNotFoundException::new);

        videoDetails.addComment(comment);
        videoRepository.save(videoDetails);
    }

    @Override
    public List<Comment> findAllComments(UUID id) {
        VideoDetails videoDetails = videoRepository.findById(id)
                .orElseThrow(VideoNotFoundException::new);

        return videoDetails.getComments();
    }
}
