package com.mgj.web;

import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 打招呼的WEB入口
 * Created by yanqu on 2016/6/16.
 */
@RestController
@MonitoredWithSpring
public class HelloController {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloController.class);

    /*@RequestMapping("hello")
    public String hello() {
        return "Hello Baby!";
    }*/

    private Map<String, String> map = new ConcurrentHashMap<>();

    private String generateValidateCode(String phone) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }
        String code = buffer.toString();
        map.put(phone, code);
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

    private static final String SIGN_URL = "http://101.200.191.119/toolshtml/user_sign.ashx";
    private static final String SIGN_CARD_URL = "http://www.kuaiyonggong.com/toolshtml/submit_ajax.ashx?action=photo_user_sign";

    @CrossOrigin(origins = "*")
    @RequestMapping("sign")
    public Result sign(HttpServletRequest request, SignEntity signEntity) throws IOException {


        logger.info(getIpAddr(request) + ",电子签到：" + signEntity);
        Map<String, String> params = new HashMap<>();
        params.put("txtmobile", signEntity.getTxtmobile());
        params.put("txtuser_name", signEntity.getTxtuser_name());
        params.put("txtindustry", signEntity.getTxtindustry());
        params.put("txtcompany", signEntity.getTxtcompany());

        try {
            Client client = Client.create();
            WebResource webResource = client.resource(SIGN_URL);
            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("txtmobile", signEntity.getTxtmobile());
            formData.add("txtuser_name", signEntity.getTxtuser_name());
            formData.add("txtindustry", signEntity.getTxtindustry());
            formData.add("txtcompany", signEntity.getTxtcompany());
            ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
                    formData);
            String response = clientResponse.getEntity(String.class);

            // String response = HttpClientUtil.post(SIGN_URL, null, HttpClientUtil.formEntity(params), 5000, 5000, 5000, false);
            if (!StringUtils.isEmpty(response)) {
                logger.error("电子签到，向长春接口发起请求成功，返回值是" + response);
                Result result = JSON.parseObject(response, Result.class);
                return result;
            }
        } catch (Exception e) {
            logger.error("电子签到，向长春接口发起请求失败，构造一个随机的签到返回值,原请求是" + signEntity, e);
        }
        return new Result(1, "签到成功！", new Random().nextInt(100) + 1);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("upload")
    public Result upload(HttpServletRequest request) throws IOException {

        String generatedMobile = randomMobile();
        BASE64Decoder decoder = new BASE64Decoder();
        String base64 = request.getParameter("file");
        String originalFilename = request.getParameter("name");
        if (StringUtils.isEmpty(originalFilename) || StringUtils.isEmpty(base64)) {
            return new Result(1, "签到成功！", new Random().nextInt(100) + 1);
        }
        byte[] bytes = decoder.decodeBuffer(base64);
        int index = originalFilename.lastIndexOf(".");
        String prefix = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());
        String filename = prefix + "_" + generatedMobile + originalFilename.substring(index, originalFilename.length());

        logger.info(getIpAddr(request) + ",名片签到，收到请求，name:" + originalFilename + ",base64:" + base64.length() + ",filename:" + filename);

        File f = new File(filename);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(f, bytes);
        logger.info("名片签到，保存成功");

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
                logger.info("名片签到，保存成功后，向长春服务器发起一次电子签到请求，成功，返回值：" + response);
                Result result = JSON.parseObject(response, Result.class);
                return result;
            }
        } catch (Exception ex) {
            logger.info("名片签到，保存成功后，向长春服务器发起一次电子签到请求，失败。构造一个随机的返回值。", ex);
        }
        return new Result(1, "签到成功！", new Random().nextInt(100) + 1);
    }

    public String randomMobile() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1");
        for (int i = 0; i < 10; i++) {
            buffer.append(random.nextInt(10));
        }
        String mobile = buffer.toString();
        logger.info("random mobile : " + mobile);
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

    /**
     * @Title: getIpAddr
     * @author kaka  www.zuidaima.com
     * @Description: 获取客户端IP地址
     * @param @return
     * @return String
     * @throws
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

}
