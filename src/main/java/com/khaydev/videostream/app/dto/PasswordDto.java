package com.khaydev.videostream.app.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordDto(@NotBlank String token, @NotBlank String newPassword) {
}
