package com.khaydev.videostream.app.controller.admin;

import com.khaydev.videostream.app.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    public ResponseEntity<?> createRole(@RequestBody Role role){
        return null;
    }
}
