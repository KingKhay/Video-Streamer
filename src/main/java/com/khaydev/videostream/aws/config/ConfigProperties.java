package com.khaydev.videostream.aws.config;


import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "aws")
public record ConfigProperties(
        String access_key,
        String secret_key
) {

}
