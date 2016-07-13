package com.mgj;

import com.mgj.util.upyun.PicBucket;
import com.mgj.util.upyun.UpyunBucketFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by yanqu on 2016/6/24.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean
    public PicBucket picBucket() {
        String bucketName = "studease";
        String username = "studease123321";
        String userPwd = "excel_916";
        return UpyunBucketFactory.build(bucketName, username, userPwd);
    }
}
