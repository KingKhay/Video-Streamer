package com.khaydev.videostream.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"username","email"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column
    private UUID id;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "\\S+.*\\S+", message = "Username cannot have leading or trailing spaces")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*\\S).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long")
    private String password;

    @NotBlank(message = "first name is required")
    @Pattern(regexp = "\\S+.*\\S+", message = "first name cannot have leading or trailing spaces")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Pattern(regexp = "\\S+.*\\S+", message = "last name cannot have leading or trailing spaces")
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)

    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Email
    @NotNull
    @Column(name = "email")
    @NotBlank(message = "email is required")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotBlank(message = "date of birth is required")
    @Pattern(regexp = "\\S+.*\\S+", message = "date cannot have leading or trailing spaces")
    private LocalDate dob;

    @Pattern(regexp = "\\S+.*\\S+", message = "image url cannot have leading or trailing spaces")
    private String imageUrl;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<VideoDetails> videosUploaded;


    public void addVideo(VideoDetails video) {
        if (videosUploaded == null) {
            videosUploaded = new ArrayList<>();
        }

        videosUploaded.add(video);
        video.setUser(this);
    }
}