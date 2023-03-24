package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByUsername(String username);

    List<User> findUsersByUsernameLikeIgnoreCase(String username);

}
