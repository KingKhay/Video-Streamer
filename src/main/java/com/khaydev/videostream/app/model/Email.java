package com.khaydev.videostream.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {

    private String from;
    private String to;
    private String subject;
    private String text;
}
