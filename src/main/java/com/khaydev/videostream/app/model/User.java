package com.khaydev.videostream.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"username","email"})
})
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column
    private UUID id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<VideoDetails> videosUploaded;


    public void addVideo(VideoDetails video){
        if (videosUploaded == null) {
            videosUploaded = new HashSet<>();
        }

        videosUploaded.add(video);
        video.setUser(this);
    }
}
