package com.khaydev.videostream.app.controller.admin;

import com.khaydev.videostream.app.model.Role;
import com.khaydev.videostream.app.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/")
    public ResponseEntity<String> createRole(@RequestBody Role role){
        service.createRole(role);
        return ResponseEntity.ok("Role created");
    }
}
