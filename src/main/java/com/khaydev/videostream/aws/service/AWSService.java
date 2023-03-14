package com.khaydev.videostream.aws.service;

import com.amazonaws.services.s3.model.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AWSService {

    void createS3Bucket(String bucketName);

    List<Bucket> listBuckets();

    void deleteBucket(String bucketName);

    String saveVideo(MultipartFile file);

}
