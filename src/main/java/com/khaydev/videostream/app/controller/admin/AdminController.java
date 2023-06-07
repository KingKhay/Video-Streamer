package com.khaydev.videostream.app.controller.admin;

import com.khaydev.videostream.app.dto.RoleDTO;
import com.khaydev.videostream.app.model.User;
import com.khaydev.videostream.app.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody RoleDTO role){
        service.createRole(role);
        return ResponseEntity.ok("Role created");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<String> addAdmin(@PathVariable UUID id){
        service.addAdmin(id);

        return new ResponseEntity<>("Admin Role added successfully",HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> users = service.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
