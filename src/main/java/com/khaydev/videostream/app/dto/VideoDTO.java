package com.khaydev.videostream.app.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoDTO {

    private UUID videoId;

    private String name;

    private String resourceUrl;
}
