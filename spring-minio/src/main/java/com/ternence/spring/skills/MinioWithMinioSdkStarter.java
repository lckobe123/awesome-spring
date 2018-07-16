package com.ternence.spring.skills;

import io.minio.MinioClient;

public class MinioWithMinioSdkStarter {

    public static void main(String[] args) {
        try {
            String endpoint = "http://192.168.72.128:9000";
            String accessKey = "3HP7LEP1JJ971NA5IM6V";
            String secureKey = "QM1Y6ChicoVdb73ZNyvltEFIzsNlkprRntZYDgq0";
            String bucketName = "ternence";
            MinioClient client = new MinioClient(endpoint, accessKey, secureKey);

            if (!client.bucketExists(bucketName)) {
                client.makeBucket(bucketName);
            } else {
                System.out.println(bucketName + "已经存在");
                client.putObject(bucketName,"/path/to/desktop.png",
                        "C:\\Users\\Ternence\\Pictures\\desktop.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
