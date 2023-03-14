package com.khaydev.videostream.aws.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AWSServiceImpl implements AWSService {

    @Value("${bucket.name}")
    String bucketName;

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

    @Override
    public String saveVideo(MultipartFile file) {

        String extension  = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String key = UUID.randomUUID() + "." + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try{
            s3Client.putObject(bucketName, key, file.getInputStream(), metadata);
        }catch(IOException ex){
            throw new RuntimeException();
        }
        finally {
            s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
        }

        return s3Client.getUrl(bucketName, key).toString();
    }
}
