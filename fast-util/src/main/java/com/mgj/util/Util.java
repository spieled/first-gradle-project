package com.mgj.util;

import org.apache.http.util.Asserts;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanqu on 2016/6/24.
 */
public class Util {

    private static final Random random = new Random();
    private static final char[] digits = new char[] {'0','1','2','3','4','5','6','7','8','9'};
    private static final char[] hexes = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public static int randomInt(int length) {
        Asserts.check(length > 0, "random int length must bigger than zero");
        char[] chars = new char[length];
        int index = 0;
        do {
            char c = digits[random.nextInt(length)];
            if (index == 0 && c == '0') {
                // do nothing, because digit first must not be zero
            }
            chars[index] = c;
            index ++;
        } while (index < length);
        return Integer.parseInt(new String(chars));
    }

    public static String randomHex(int length) {
        Asserts.check(length > 0, "random int length must bigger than zero");
        char[] chars = new char[length];
        int index = 0;
        do {
            char c = hexes[random.nextInt(length)];
            if (index == 0 && c == '0') {
                // do nothing, because digit first must not be zero
            }
            chars[index] = c;
            index ++;
        } while (index < length);
        return new String(chars);
    }

    public static String generateValidateCode() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }
        String code = buffer.toString();
        return code;
    }

    public static final List<Object> singleList(Object object) {
        ArrayList list = new ArrayList();
        list.add(object);
        return list;
    }

    public static String randomMobile() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1");
        for (int i = 0; i < 10; i++) {
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
