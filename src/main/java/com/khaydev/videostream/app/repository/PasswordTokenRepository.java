package com.khaydev.videostream.app.repository;

import com.khaydev.videostream.app.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}
