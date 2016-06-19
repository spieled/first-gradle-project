package com.mgj.web;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 打招呼的WEB入口
 * Created by yanqu on 2016/6/16.
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "Hello Baby!";
    }

    @RequestMapping("upload")
    public String upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        for (List<MultipartFile> list : req.getMultiFileMap().values()) {
            MultipartFile file = list.get(0);
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String prefix = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());
            String filename = prefix + originalFilename.substring(index, originalFilename.length());
            File f = new File("D:\\" + filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            file.transferTo(f);
        }

        return "success";
    }
}
