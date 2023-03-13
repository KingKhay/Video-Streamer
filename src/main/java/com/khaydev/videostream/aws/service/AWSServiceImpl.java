package com.khaydev.videostream.aws.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AWSServiceImpl implements AWSService {

    private final Logger LOG = LoggerFactory.getLogger(AWSServiceImpl.class);
    private final AmazonS3 s3Client;
    public AWSServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }
    @Override
    public void createS3Bucket(String bucketName) {
        if(s3Client.doesBucketExist(bucketName)){
            LOG.info("Bucket name already exists");
        }

        s3Client.createBucket(bucketName);
    }

    @Override
    public List<Bucket> listBuckets() {
        return s3Client.listBuckets();
    }

    @Override
    public void deleteBucket(String bucketName) {
        try {
            s3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}
