package com.mgj.web;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
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

    @CrossOrigin(origins = "*")
    @RequestMapping("upload")
    public String upload(HttpServletRequest request) throws IOException {
        /*, @RequestParam("file") MultipartFile[] files*/

        BASE64Decoder decoder = new BASE64Decoder();
        String base64 = request.getParameter("file");
        String originalFilename = request.getParameter("name");
        byte[] bytes = decoder.decodeBuffer(base64);
        int index = originalFilename.lastIndexOf(".");
        String prefix = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());
        String filename = prefix + originalFilename.substring(index, originalFilename.length());
        File f = new File("D:\\" + filename);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(f, bytes);
        // decodeBase64ToImage(base64, "D:\\", filename);

        /*MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
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
        }*/

        return "success";
    }

    public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
