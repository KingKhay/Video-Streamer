package com.khaydev.videostream.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class VideoDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "video_id")
    private UUID videoId;

    @NotBlank(message = "video name is required")
    @Pattern(regexp = "\\S+.*\\S+", message = "video name cannot have leading or trailing spaces")
    private String videoName;

    private String resourceUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dateUploaded;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate dateUpdated;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "video")
    private List<Comment> comments;

    @PrePersist
    protected void onUpload(){
        this.dateUploaded = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.dateUpdated = LocalDate.now();
    }

    public void addComment(Comment comment){
        if (comments == null) {
            comments = new ArrayList<>();
        }

        comments.add(comment);

        comment.setVideo(this);
    }
}
