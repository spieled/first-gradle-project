package com.mgj.web;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
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
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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

    private Map<String, String> map = new ConcurrentHashMap<>();
    private String generateValidateCode(String phone) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i=0; i<6; i++) {
            buffer.append(random.nextInt(10));
        }
        String code = buffer.toString();
        map.put(phone,code);
        return code;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("sendValidateCode")
    public String sendValidateCode(HttpServletRequest request, String phone) throws IOException {
        String message = "验证码" + generateValidateCode(phone);
        String sign = "快WIFI";
        LuosimaoSendUtil.send(phone, message, sign);
        return "success";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("validateCode")
    public String validateCode(HttpServletRequest request, String phone, String code) throws IOException {
        String savedCode = map.get(phone);
        if (!StringUtils.isEmpty(savedCode) && savedCode.equals(code)) {
            return "success";
        }
        return "failed";
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
