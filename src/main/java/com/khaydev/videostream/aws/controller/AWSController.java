package com.khaydev.videostream.aws.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.khaydev.videostream.aws.model.AwsDTO;
import com.khaydev.videostream.aws.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aws")
public class AWSController {

    @Autowired
    private AWSService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createBucket(@RequestBody AwsDTO awsDTO){
        service.createS3Bucket(awsDTO.getBucketName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<Bucket> listBuckets(){
        return service.listBuckets();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/")
    public void deleteBucket(@RequestBody AwsDTO awsDTO){
        service.deleteBucket(awsDTO.getBucketName());
    }
}
