package com.khaydev.videostream.app.controller.user;

import com.khaydev.videostream.app.dto.UserDTO;
import com.khaydev.videostream.app.dto.VideoDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id){
        UserDTO user = service.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        List<UserDTO> users = service.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
        service.save(user);
        return new ResponseEntity<>("User Successfully Saved", HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable UUID id){
        return ResponseEntity.ok(service.updateUser(user, id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoDTO>> getAllVideos(@PathVariable UUID id){
        return ResponseEntity.ok(service.findVideos(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.findUserByUsername(username));
    }
}
