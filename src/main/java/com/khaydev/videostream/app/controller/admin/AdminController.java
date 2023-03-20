package com.khaydev.videostream.app.controller.admin;

import com.khaydev.videostream.app.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        return null;
    }
}
