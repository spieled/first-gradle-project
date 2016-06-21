package com.mgj.web;

import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private static final String SIGN_URL = "http://www.kuaiyonggong.com/toolshtml/user_sign.ashx";
    private static final String SIGN_CARD_URL = "http://www.kuaiyonggong.com/toolshtml/submit_ajax.ashx?action=photo_user_sign";

    @CrossOrigin(origins = "*")
    @RequestMapping("sign")
    public Result sign(HttpServletRequest request, SignEntity signEntity) throws IOException {
        try {

            Map<String, String> params = new HashMap<>();
            params.put("txtmobile", signEntity.getTxtmobile());
            params.put("txtuser_name", signEntity.getTxtuser_name());
            params.put("txtindustry", signEntity.getTxtindustry());
            params.put("txtaddress", signEntity.getTxtaddress());

            Client client = Client.create();
            WebResource webResource = client.resource(SIGN_URL);
            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("txtmobile", signEntity.getTxtmobile());
            formData.add("txtuser_name", signEntity.getTxtuser_name());
            formData.add("txtindustry", signEntity.getTxtindustry());
            formData.add("txtaddress", signEntity.getTxtaddress());
            ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
                    formData);
            String response = clientResponse.getEntity(String.class);

            // String response = HttpClientUtil.post(SIGN_URL, null, HttpClientUtil.formEntity(params), 5000, 5000, 5000, false);
            if (!StringUtils.isEmpty(response)) {
                Result result = JSON.parseObject(response, Result.class);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(1, "签到成功！", new Random().nextInt(100)+1);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("upload")
    public Result upload(HttpServletRequest request) throws IOException {

        String generatedMobile = randomMobile();
        BASE64Decoder decoder = new BASE64Decoder();
        String base64 = request.getParameter("file");
        String originalFilename = request.getParameter("name");
        byte[] bytes = decoder.decodeBuffer(base64);
        int index = originalFilename.lastIndexOf(".");
        String prefix = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());
        String filename = prefix + "_" + generatedMobile + originalFilename.substring(index, originalFilename.length());
        File f = new File("D:\\" + filename);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(f, bytes);

        try {
            Map<String, String> params = new HashMap<>();
            params.put("txtmobile", generatedMobile);
            params.put("txtuser_name", filename);
            Client client = Client.create();
            WebResource webResource = client.resource(SIGN_URL);
            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("txtmobile", generatedMobile);
            formData.add("txtuser_name", filename);
            ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
                    formData);
            String response = clientResponse.getEntity(String.class);

            // String response = HttpClientUtil.post(SIGN_URL, null, HttpClientUtil.formEntity(params), 5000, 5000, 5000, false);
            // 向长春服务器发起一次电子签到请求
            if (!StringUtils.isEmpty(response)) {
                Result result = JSON.parseObject(response, Result.class);
                return result;
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        return new Result(1, "签到成功！", new Random().nextInt(100)+1);
    }

    public static String randomMobile() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1");
        for (int i=0; i<10; i++) {
            buffer.append(random.nextInt(10));
        }
        String mobile = buffer.toString();
        return mobile;
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
