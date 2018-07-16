package com.ternence.spring.skills;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/7/16 22:20
 */
public class MinioWithAwsSdkStarter {
    public static void main(String[] args) {
        String endpoint = "http://192.168.72.128:9000";
        String accessKey = "3HP7LEP1JJ971NA5IM6V";
        String secureKey = "QM1Y6ChicoVdb73ZNyvltEFIzsNlkprRntZYDgq0";
        String bucketName = "ternence";
        //aws的规则是以数字、字母开始，不能以别的字符开始
        String key = "2018/07/16/desktop.png";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secureKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider
                (credentials);
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.
                        EndpointConfiguration(endpoint, Regions.CN_NORTH_1.getName()))
                //.withPathStyleAccessEnabled(true)
                .withCredentials(credentialsProvider)
                .build();
        amazonS3.putObject(bucketName, key,
                new File("C:\\Users\\Ternence\\Pictures\\desktop.png"));
        System.out.println("上传完毕");

        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,key);
        ObjectMetadata metadata= amazonS3.getObject(getObjectRequest,
                new File("C:\\Users\\Ternence\\Pictures\\desktop-download.png"));
        System.out.println("下载完成:"+metadata);

       /* try {
            S3ObjectInputStream objStream = s3Object.getObjectContent();
            byte[] data = new byte[1024];
            FileOutputStream outputStream = new FileOutputStream(
                    new File("C:\\Users\\Ternence\\Pictures\\desktop-download.png"));
            while (objStream.read(data) > 0) {
                outputStream.write(data);
            }
            outputStream.flush();
            outputStream.close();
            s3Object.close();
            System.out.println("下载完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
