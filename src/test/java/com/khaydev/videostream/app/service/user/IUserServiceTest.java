package com.khaydev.videostream.app.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.exception.user.UserNotFoundException;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import com.khaydev.videostream.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IUserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private IUserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find_Users_By_Id")
    void testFindUserByIdSuccess(){
        UUID id = UUID.randomUUID();

        User user = new User();
        user.setUsername("Khay");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Khay");

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(mapper.convertValue(any(), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.findUserById(id);

        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Find_User_By_Id_User_Not_Found")
    void testFindUserById_UserNotFound(){
        UUID userId = UUID.randomUUID();

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
           userService.findUserById(userId);
        });

        verify(mapper, never()).convertValue(any(), eq(UserDTO.class));
    }

    @Test
    @DisplayName("Update_User_Successful")
    void testUpdateUserSuccess(){
        UUID userId = UUID.randomUUID();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Khay");

        User user = new User();
        user.setId(userId);
        user.setFirstName("Khay");

        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(any())).thenReturn(user);
        when(mapper.convertValue(any(), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.updateUser(userDTO, userId);

        assertNotNull(result);
        assertEquals(userDTO.getFirstName(), result.getFirstName());

        verify(mapper, times(1)).convertValue(user, UserDTO.class);
    }

    @Test
    @DisplayName("Update_User_User_Not_Found")
    void testUpdateUser_UserNotFound(){
        UUID userId = UUID.randomUUID();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Khay");

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userDTO, userId);
        });

        verify(mapper, never()).convertValue(any(), eq(UserDTO.class));
    }

    @Test
    @DisplayName("Find_Videos_Of_User_Success")
    void testFindVideosOfUser(){
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setFirstName("Khay");

        List<VideoDetails> videos = new ArrayList<>();
        VideoDetails video1 = new VideoDetails();
        video1.setVideoName("Video 1");
        videos.add(video1);

        VideoDetails video2 = new VideoDetails();
        video2.setVideoName("Video 2");
        videos.add(video2);

        user.setVideosUploaded(videos);

        VideoDTO videoDTO1 = new VideoDTO();
        videoDTO1.setName("Video 1");
        VideoDTO videoDTO2 = new VideoDTO();
        videoDTO2.setName("Video 2");

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        when(mapper.convertValue(any(), eq(VideoDTO.class))).thenReturn(videoDTO1, videoDTO2);

        List<VideoDTO> result = userService.findVideos(userId);

        assertNotNull(result);

        assertEquals(2, result.size());

        verify(repository, times(1)).findById(userId);
        verify(mapper, times(2)).convertValue(any(), eq(VideoDTO.class));
    }

    @Test
    @DisplayName("Search_Users_By_Username")
    void testSearchUsersByUsername(){

        String username = "khay";

        User user = new User();
        user.setUsername(username);

        User secondUser = new User();
        secondUser.setUsername(username);

        List<User> users = List.of(user, secondUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);

        UserDTO secondUserDTO = new UserDTO();
        secondUserDTO.setUsername(username);

        when(repository.findUsersByUsernameContainingIgnoreCase(username)).thenReturn(users);
        when(mapper.convertValue(any(), eq(UserDTO.class))).thenReturn(userDTO, secondUserDTO);

        List<UserDTO> result = userService.searchUsersByUsername(username);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(mapper, times(2)).convertValue(any(), eq(UserDTO.class));
    }

    @Test
    @DisplayName("Search_Users_By_Username_No_Users_Found")
    void testSearchByUsernameNoUsersFound(){
        String username = "khay";

        List<User> users = List.of();
        when(repository.findUsersByUsernameContainingIgnoreCase(username)).thenReturn(users);

        List<UserDTO> result = userService.searchUsersByUsername(username);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(mapper, never()).convertValue(any(), eq(UserDTO.class));
    }
}