package com.khaydev.videostream.app.controller.user;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.model.VideoDetails;
import com.khaydev.videostream.app.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable UUID id){
        return service.findUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<User> findAllUsers(){
        return service.findAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public UserDTO registerUser(@Valid @RequestBody User user){
        return service.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO user, @PathVariable UUID id){
        return service.updateUser(user, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable UUID id){
        return service.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/videos")
    public List<VideoDTO> getAllVideos(@PathVariable UUID id){
        return service.findVideos(id);
    }
}
