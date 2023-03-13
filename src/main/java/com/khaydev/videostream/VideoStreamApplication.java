package com.khaydev.videostream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.khaydev.videostream")
public class VideoStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoStreamApplication.class, args);
    }

}
