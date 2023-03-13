package com.khaydev.videostream.aws.service;

import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public interface AWSService {

    public void createS3Bucket(String bucketName);

    public List<Bucket> listBuckets();

    public void deleteBucket(String bucketName);
}
