package com.mgj.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;
import com.mgj.base.Dir;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Transient;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

/**
 * Created by yanqu on 2016/6/24.
 */
public class Util {

    private static final Random random = new Random();
    private static final char[] digits = new char[] {'0','1','2','3','4','5','6','7','8','9'};
    private static final char[] hexes = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    private Util() {
    }

    private static final Util __instance__ = new Util();

    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final Map<String, Class<?>> businessMap = new ConcurrentHashMap<>();

    private static final ThreadLocal<WebApplicationContext> wacThreadLocal = new ThreadLocal<>();

    public static String lowerFirstChar(String s) {
        Asserts.check(!s.isEmpty(), "input string should not be empty");
        return s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
    }

    public static String deleteLastChar(String s, String target) {
        if (s.endsWith(target)) {
            return s.substring(0, s.length()-target.length());
        }
        return s;
    }

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
        List<Object> list = new ArrayList();
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






        /**
         * 获取客户端IP地址
         */
        public static String getIp(HttpServletRequest request) {
            if (request == null) {
                return "";
            }
            String ip = request.getHeader(Constants.X_REAL_FORWARDED_FOR);
            if (ip == null || ip.length() == 0 || Constants.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(Constants.X_FORWARDED_FOR);
                if (ip == null || ip.length() == 0 || Constants.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            }
            if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) { // "***.***.***.***".length() = 15
                ip = ip.substring(0, ip.indexOf(","));
            }
            return ip;
        }

        /**
         * 将source对象内的成员变量复制给target对象中的同名变量。
         */
        public static <S, T> T dump(S source, T target) {
            if (source == null) {
                return target;
            }
            List<Method> methods = new ArrayList<>();
            Class<?> c = source.getClass();
            while (!c.getName().equals(Constants.CLASS_OBJECT)) {
                methods.addAll(Arrays.asList(c.getDeclaredMethods()));
                c = c.getSuperclass();
            }
            for (Method method : methods) {
                try {
                    String methodName = method.getName(), setter;
                    if (methodName.startsWith(Constants.GET)) {
                        setter = Constants.SET + methodName.substring(3);
                    } else if (methodName.startsWith(Constants.IS)) {
                        setter = Constants.SET + methodName.substring(2);
                    } else {
                        continue;
                    }
                    target.getClass().getMethod(setter, method.getReturnType()).invoke(target, method.invoke(source));
                } catch (Exception ignored) {
                    log.trace("dump出现异常，通常是由于源或目标缺少指定字段造成的。", ignored);
                }
            }
            return target;
        }

        /**
         * 选择性复制
         */
        public static <S, T> T dump(S source, T target, String... fields) {

            // 如果源对象为null，就跳过不做任何操作
            if (source == null) {
                return target;
            }

            // 先得到两种对象的具体类型
            Class<?> sourceType = source.getClass(), targetType = target.getClass();

            // 每个字段循环复制
            for (String field : fields) {
                try {
                    Util.getSetter(targetType, field).invoke(target, Util.getGetter(sourceType, field).invoke(source));
                } catch (Exception e) {
                    log.trace("dump出现异常，通常是由于源或目标缺少指定字段造成的。", e);
                }
            }
            return target;
        }

        /**
         * 深度克隆
         */
        public static <T> T clone(T object) {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteStream);
                out.writeObject(object);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteStream.toByteArray()));
                T cloned = (T) in.readObject();
                in.close();

                return cloned;
            } catch (Exception e) {
                log.trace("克隆对象失败", e);
                return null;
            }
        }

        /**
         * 从类中得到指定字段的Getter方法
         */
        public static <T> Method getGetter(Class<T> clazz, String field) {
            try {
                String prefix = Util.getField(clazz, field).getType().getSimpleName().toLowerCase().equals("boolean") ? "is" : "get";
                String methodName = prefix + Util.upperCaseFirstChar(field);
                return clazz.getMethod(methodName);
            } catch (Exception e) {
                log.trace("获取Getter方法失败", e);
                return null;
            }
        }

        /**
         * 从类中得到指定字段的Setter方法
         */
        public static <T> Method getSetter(Class<T> clazz, String field) {
            try {
                Class<?> type = Util.getField(clazz, field).getType();
                String methodName = "set" + Util.upperCaseFirstChar(field);
                return clazz.getMethod(methodName, type);
            } catch (Exception e) {
                log.trace("获取Setter方法失败", e);
                return null;
            }
        }

        /**
         * 从类中获取指定名字的Method方法
         */
        public static <T> Method getMethod(Class<T> clazz, String methodName, Class<?>... parameterTypes) {
            try {
                return clazz.getMethod(methodName, parameterTypes);
            } catch (Exception e) {
                log.trace("获取Method失败", e);
                return null;
            }
        }

        /**
         * 判断字符串是否有具体内容
         */
        public static boolean hasText(String str) {
            return StringUtils.isNotBlank(str);
        }

        /**
         * 判断数组是否有具体内容
         */
        public static boolean hasText(String[] array) {
            return array != null && array.length > 0 && array[0] != null && array[0].length() > 0;
        }

        /**
         * 判断当前的数据是否真的有值，不等于零。
         */
        public static boolean hasValue(BigDecimal bigDecimal) {
            return bigDecimal != null && bigDecimal.doubleValue() != 0;
        }

        /**
         * 保存COOKIE
         */
        public static Cookie addCookie(HttpServletResponse response, String name, String value, int maxAgeSeconds) {
            if (!Util.hasText(name)) {
                return null;
            }
            try {
                String path = Util.getHttpServletRequest().getContextPath();
                if (!Util.hasText(path)) {
                    path = Constants.SLASH;
                }
                Cookie cookie = new Cookie(name, value);
                cookie.setMaxAge(maxAgeSeconds);
                cookie.setPath(path);
                response.addCookie(cookie);
                return cookie;
            } catch (Exception e) {
                log.trace("添加COOKIE失败", e);
                return null;
            }
        }

        /**
         * 读取指定的COOKIE
         *
         * @param name COOKIE的名称
         */
        public static Cookie getCookie(String name) {
            Cookie[] cookies = Util.getHttpServletRequest().getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return cookie;
                    }
                }
            }
            return null;
        }

        /**
         * 删除指定的COOKIE
         *
         * @param response 返回页面
         * @param name     COOKIE的名称
         */
        public static void deleteCookie(HttpServletResponse response, String name) {
            if (!Util.hasText(name)) {
                return;
            }
            Cookie[] cookies = Util.getHttpServletRequest().getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }

        /**
         * 用AES算法加密
         */
        public static String aesEncrypt(String plainText, String key) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(Constants.AES);
                SecureRandom secureRandom = SecureRandom.getInstance(Constants.SHA1PRNG);
                secureRandom.setSeed(key.getBytes());
                kgen.init(128, secureRandom);
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, Constants.AES);
                Cipher cipher = Cipher.getInstance(Constants.AES);
                byte[] byteContent = plainText.getBytes(Constants.UTF_8);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                return byte2hex(cipher.doFinal(byteContent));
            } catch (Exception e) {
                throw new RuntimeException("采用AES算法加密失败", e);
            }
        }

        /**
         * 用AES算法解密
         */
        public static String aesDecrypt(String cipherText, String key) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(Constants.AES);
                SecureRandom secureRandom = SecureRandom.getInstance(Constants.SHA1PRNG);
                secureRandom.setSeed(key.getBytes());
                kgen.init(128, secureRandom);
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, Constants.AES);
                Cipher aes = Cipher.getInstance(Constants.AES);
                aes.init(Cipher.DECRYPT_MODE, keySpec);
                return new String(aes.doFinal(hex2byte(cipherText)));
            } catch (Exception e) {
                throw new RuntimeException("采用AES算法解密失败", e);
            }
        }

        /**
         * 执行MD5加密，微调过HASH结果
         */
        public static String md5(String plainText) {
            if (!Util.hasText(plainText)) {
                return "";
            }
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] md5Bytes = md5.digest(plainText.getBytes());
                byte[] special = {2, 3, 7, 11, 14};
                for (byte i : special) {
                    int b = md5Bytes[i];
                    b = b + i;
                    if (b > 127) {
                        b = b - 127;
                    }
                    md5Bytes[i] = (byte) b;
                }
                return byte2hex(md5.digest(md5Bytes));
            } catch (Exception e) {
                throw new RuntimeException("采用MD5算法加密失败", e);
            }
        }

        /**
         * 执行原生的MD5加密
         */
        public static String md5Raw(String plainText) {
            if (!Util.hasText(plainText)) {
                return "";
            }
            try {
                return byte2hex(MessageDigest.getInstance(Constants.MD5).digest(plainText.getBytes(Constants.UTF_8)));
            } catch (Exception e) {
                throw new RuntimeException("采用MD5算法加密失败", e);
            }
        }

        /**
         * 取字符串的SHA1值
         */
        public static String sha1(String str) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(str.getBytes(Constants.UTF_8));
                byte[] bytes = messageDigest.digest();
                int len = bytes.length;
                StringBuilder buf = new StringBuilder(len * 2);
                for (int j = 0; j < len; j++) {
                    buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
                    buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
                }
                return buf.toString();
            } catch (Exception e) {
                throw new RuntimeException("采用SHA1算法加密失败", e);
            }
        }

        /**
         * 从输入流中读取成字符串，多用于文本文件的读取。
         */
        public static String readStreamString(InputStream inputStream) {
            if (inputStream == null) {
                return Constants.EMPTY;
            }
            StringBuffer out = new StringBuffer();
            try {
                byte[] b = new byte[4096];
                for (int n; (n = inputStream.read(b)) != -1; ) {
                    out.append(new String(b, 0, n, Constants.UTF_8));
                }
                return out.toString();
            } catch (Exception e) {
                throw new RuntimeException("读取流失败", e);
            }
        }

        /**
         * 将数据流读取存为字节数组，多用于二进制文件的读取。
         */
        public static byte[] readStreamBytes(InputStream inputStream) {
            try {
                BufferedInputStream bufin = new BufferedInputStream(inputStream);
                int buffSize = 4096, size;
                ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
                byte[] temp = new byte[buffSize];
                while ((size = bufin.read(temp)) != -1) {
                    out.write(temp, 0, size);
                }
                inputStream.close();
                return out.toByteArray();
            } catch (Exception e) {
                return new byte[0];
            }
        }


        /**
         * 将字节码转换成十六进制形式
         */
        public static String byte2hex(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        }

        /**
         * 将十六进制形式的字符串转换成字节码数组
         */
        public static byte[] hex2byte(String hex) {
            if (hex.length() < 1) {
                return null;
            }
            byte[] result = new byte[hex.length() / 2];
            for (int i = 0; i < hex.length() / 2; i++) {
                int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }

        /**
         * 取当前的年月日
         */
        public static int parseYmd() {
            return parseYmd(System.currentTimeMillis());
        }

        public static int parseYmd(Date date) {
            return parseYmd(date.getTime());
        }

        /**
         * 取具体时间的年份
         */
        public static int parseYear(long time) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            return calendar.get(Calendar.YEAR);
        }

        /**
         * 把一个YMD值转换成年费
         */
        public static int parseYear(int someYmd) {
            return someYmd / 10000;
        }

        /**
         * 取当前年份
         */
        public static int parseYear() {
            return parseYear(System.currentTimeMillis());
        }

        /**
         * 根据具体时间取对应的年月日
         */
        public static int parseYmd(long time) {
            return Integer.parseInt(DateFormatUtils.format(time, Constants.YMD));
        }

        public static int parseYm() {
            return parseYm(System.currentTimeMillis());
        }

        /**
         * 根据具体时间取对应的年月
         */
        public static int parseYm(long time) {
            return Integer.parseInt(DateFormatUtils.format(time, Constants.YM));
        }


        public static int parseYm(int someYmd) {
            return someYmd / 100;
        }

        public static List<Integer> parseYmsBetween(int startYmd, int endYmd) {
            List<Integer> set = new ArrayList<>();
            for (int someYmd : Util.parseYmdsBetween(startYmd, endYmd)) {
                int someYm = Util.parseYm(someYmd);
                if (!set.contains(someYm)) {
                    set.add(someYm);
                }
            }
            return set;
        }


        public static List<Integer> parseYmsBetween(String startDate, String endDate) {
            return parseYmsBetween(Util.parseYmd(startDate), Util.parseYmd(endDate));
        }

        public static List<Integer> parseYmsBetween(Date startDate, Date endDate) {
            return parseYmsBetween(Util.parseYmd(startDate.getTime()), Util.parseYmd(endDate.getTime()));
        }

        public static int parseYmd(String date) {
            return Util.parseInt(date.replaceAll("-", Constants.EMPTY), 0);
        }

        public static long parseStartTimestamp(int someYmd) {
            return parseStartDate(someYmd).getTime();
        }

        public static Date parseStartDate(int someYmd) {
            try {
                return DateUtils.parseDate(someYmd + "", Constants.YMD);
            } catch (Exception e) {
                throw new RuntimeException("无法解析成日期：" + someYmd);
            }
        }

        public static long parseEndTimestamp(int someYmd) {
            return parseEndDate(someYmd).getTime();
        }

        public static Date parseEndDate(int someYmd) {
            try {
                return DateUtils.parseDate(someYmd + "235959999", new String[]{"yyyyMMddHHmmssSSS"});
            } catch (Exception e) {
                throw new RuntimeException("无法解析成日期：" + someYmd);
            }
        }


        public static List<Integer> parseYmdsBetween(int startYmd, int endYmd) {
            ArrayList<Integer> dates = new ArrayList<>();

            Calendar c1 = Calendar.getInstance();
            c1.setTimeInMillis(parseStartTimestamp(startYmd));
            Calendar c2 = Calendar.getInstance();
            c2.setTimeInMillis(parseStartTimestamp(endYmd));

            if (c1.compareTo(c2) > 0) {
                return dates;
            }

            while (true) {
                dates.add(Util.parseYmd(c1.getTimeInMillis()));
                c1.add(Calendar.DATE, 1);
                if (c1.compareTo(c2) > 0) {
                    break;
                }
            }
            return dates;
        }


        /**
         * 将指定的参数值转换成年月日
         */
        public static int parseYmd(HttpServletRequest request, String name) {
            try {
                return Integer.parseInt(request.getParameter(name).replaceAll(Constants.MINUS, Constants.EMPTY));
            } catch (Exception e) {
                log.trace("从HttpServletRequest取YMD对象失败", e);
                return 0;
            }
        }

        /**
         * 从当前的请求中得到startYmd参数
         */
        public static int parseStartYmd() {
            return parseStartYmd(getHttpServletRequest());
        }

        /**
         * 从请求中得到startYmd参数
         */
        public static int parseStartYmd(HttpServletRequest request) {
            try {
                return parseInt(request.getParameter(Constants.START_YMD).replaceAll(Constants.MINUS, Constants.EMPTY), 0);
            } catch (Exception e) {
                log.trace("从HttpServletRequest取YMD对象失败", e);
                return 0;
            }
        }

        /**
         * 从当前的请求中得到endYmd参数
         */
        public static int parseEndYmd() {
            return parseEndYmd(getHttpServletRequest());
        }

        /**
         * 从请求中得到endYmd参数
         */
        public static int parseEndYmd(HttpServletRequest request) {
            try {
                return parseInt(request.getParameter(Constants.END_YMD).replaceAll(Constants.MINUS, Constants.EMPTY), 0);
            } catch (Exception e) {
                log.trace("从HttpServletRequest取YMD对象失败", e);
                return 0;
            }
        }

        /**
         * 格式化日期
         */
        public static String formatDate(long time) {
            return formatDate(new Date(time));
        }


        /**
         * 格式化YMD类型的日期为标准格式
         */
        public static String formatDate(int someYmd) {
            return formatDate(parseDate(someYmd));
        }

        /**
         * 格式化日期
         */
        public static String formatDate(Date date) {
            return DateFormatUtils.format(date, Constants.Y_M_D);
        }

        public static String formatDate(Date date, String format) {
            return DateFormatUtils.format(date, format);
        }

        /**
         * 格式化时间
         */
        public static String formatDateTime(long time) {
            return DateFormatUtils.format(time, Constants.Y_M_D_H_M_S);
        }

        /**
         * 格式化时间
         */
        public static String formatDateTime(Date date) {
            return DateFormatUtils.format(date, Constants.Y_M_D_H_M_S);
        }


        /**
         * 按照指定的精度来格式化数值
         */
        public static String formatDecimal(Object object, int precision) {
            String valueStr;
            if (object == null) {
                valueStr = Constants.EMPTY;
            } else {
                try {
                    String pattern = "0." + String.format("%0" + precision + "d", 0);
                    valueStr = new DecimalFormat(pattern).format(object);
                    while (valueStr.endsWith("0")) {
                        valueStr = Util.deleteLastChar(valueStr);
                    }
                    if (valueStr.endsWith(".")) {
                        valueStr = Util.deleteLastChar(valueStr);
                    }
                } catch (Exception e) {
                    valueStr = object.toString();
                }
            }
            return valueStr;
        }

        /**
         * 格式化数字
         */
        public static String formatDecimal(Object object) {
            return formatDecimal(object, 2);
        }

        /**
         * 从当前请求中获取参数信息
         *
         * @param def 取值为空或null时返回这个值
         */
        public static String parseString(HttpServletRequest request, String name, String def) {
            String assigned = request.getParameter(name);
            return Util.hasText(assigned) ? assigned : def;
        }

        /**
         * 根据int类型的YMD日期获取Calendar对象
         */
        public static Calendar parseCalendar(int ymd) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parseDate(ymd + "", Constants.YMD, null));
                return calendar;
            } catch (Exception e) {
                log.trace("从YMD转换成Calendar对象失败", e);
                return null;
            }
        }

        /**
         * 判断开始日期和结束日期中的间隔天数是否小于等于duration值。
         */
        public static boolean isInDuration(int startYmd, int endYmd, int duration) {
            int currentYmd = parseYmd();
            startYmd = startYmd <= 0 ? currentYmd : startYmd;
            endYmd = endYmd <= 0 ? currentYmd : endYmd;
            Calendar start = parseCalendar(startYmd);
            Calendar end = parseCalendar(endYmd);
            start.add(Calendar.DAY_OF_MONTH, duration);
            return start.after(end);
        }

        /**
         * 将字符串解析成整数，如果解析失败就返回def。
         */
        public static int parseInt(String str, int def) {
            try {
                if (Util.hasText(str) && str.indexOf(Constants.MINUS) > 0) {
                    str = str.replaceAll(Constants.MINUS, Constants.EMPTY);
                }
                return Integer.parseInt(str);
            } catch (Exception e) {
                return def;
            }
        }

        /**
         * 将指定的参数值解析成整数，如果解析失败就返回def。
         */
        public static int parseInt(HttpServletRequest request, String name, int def) {
            return parseInt(request.getParameter(name), def);
        }

        /**
         * 将字符串解析成长整型，如果解析失败就返回def。
         */
        public static long parseLong(String str, long def) {
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                return def;
            }
        }

        /**
         * 将字符串解析成单精度浮点数，如果解析失败就返回def
         */
        public static float parseFloat(String str, float def) {
            try {
                return Float.parseFloat(str);
            } catch (Exception e) {
                return def;
            }
        }

        /**
         * 将字符串解析成双精度浮点数，如果解析失败就返回def
         */
        public static double parseDouble(String str, double def) {
            try {
                return Double.parseDouble(str);
            } catch (Exception e) {
                return def;
            }
        }

        /**
         * 将字符串解析成布尔型，如果解析失败就返回def。
         */
        public static boolean parseBoolean(HttpServletRequest request, String str, boolean def) {
            return parseBoolean(Util.parseString(request, str, Constants.EMPTY), def);
        }

        /**
         * 将字符串解析成布尔型，如果解析失败就返回def。
         */
        public static boolean parseBoolean(String str, boolean def) {
            if (!Util.hasText(str)) {
                return def;
            }
            str = str.toLowerCase();
            if (Arrays.asList("true", "yes", "on", "1").contains(str)) {
                return true;
            }
            if (Arrays.asList("false", "no", "off", "0").contains(str)) {
                return false;
            }
            return def;
        }

        /**
         * 将字符串按照指定格式解析成日期类型，如果解析失败返回def。
         */
        public static Date parseDate(String str, String format, Date def) {
            try {
                return DateUtils.parseDate(str, new String[]{format});
            } catch (Exception e) {
                return def;
            }
        }

        /**
         * 把YMD格式的数字转换成Date类型
         */
        public static Date parseDate(int someYmd) {
            return parseDate(someYmd + "", Constants.YMD, null);
        }


        /**
         * 将字符串解析成数值型，如果解析失败就返回def。
         */
        public static BigDecimal parseBigDecimal(String str, BigDecimal def) {
            try {
                return new BigDecimal(str);
            } catch (Exception e) {
                return def;
            }
        }

    /**
     * 将字符串解析成数值型，如果解析失败就返回def。
     */
    public static BigDecimal parseBigDecimal(HttpServletRequest request, String str, BigDecimal def) {
        try {
            return new BigDecimal(request.getParameter(str));
        } catch (Exception e) {
            return def;
        }
    }

        /**
         * 把字符串解析成枚举类型
         */
        public static <T extends Enum<T>> T parseEnumByName(Class<T> clazz, String value) {
            try {
                return Enum.valueOf(clazz, value);
            } catch (Exception e) {
                log.trace("解析枚举类型失败", e);
            }
            return null;
        }

        /**
         * 通过显示的字符串解析成枚举类型
         */
        public static <T extends Enum<T>> BaseEnum parseEnumByDisplay(Class<T> clazz, String display) {
            try {
                for (T t : EnumSet.allOf(clazz)) {
                    if (Util.equals(display, ((BaseEnum) t).display())) {
                        return (BaseEnum) t;
                    }
                }
            } catch (Exception e) {
                log.trace("从显示名称解析成枚举类型失败", e);
            }
            return null;
        }

        /**
         * 将信息存入到HttpSession中
         */
        public static void setSessionAttribute(String key, Object value) {
            Util.getHttpServletRequest().getSession().setAttribute(key, value);
        }

        /**
         * 从HttpSession中取值
         */
        public static Object getSessionAttribute(String key) {
            return Util.getHttpServletRequest().getSession().getAttribute(key);
        }


        /**
         * 获取ServletContext
         */
        public static ServletContext getServletContext() {
            return getWebApplicationContext().getServletContext();
        }

        /**
         * 将信息存为全局变量
         */
        public static void setApplicationAttribute(String key, Object value) {
            getServletContext().setAttribute(key, value);
        }

        /**
         * 从系统全局变量中取值
         */
        public static Object getApplicationAttribute(String key) {
            return getServletContext().getAttribute(key);
        }

        /**
         * 获取Spring的WebApplicationContext
         */
        public static WebApplicationContext getWebApplicationContext() {

            WebApplicationContext wac = wacThreadLocal.get();
            if (wac == null) {
                wac = ContextLoader.getCurrentWebApplicationContext();
            }

            WebApplicationContext child = (WebApplicationContext) wac.getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatch");
            return child == null ? wac : child;
        }


        /**
         * 将wac设置到本地线程中使用，主要用于Spring Test环境下不能直接用ContextLoader来取wac的问题。
         */
        public static void setWebApplicationContext(WebApplicationContext wac) {
            wacThreadLocal.set(wac);
        }


        /**
         * 向全系统发布事件
         */
        public static void publishEvent(ApplicationEvent event) {
            Util.getWebApplicationContext().publishEvent(event);
        }

        /**
         * 从Spring获取指定类型的bean
         */
        public static <T> T getBean(Class<T> clazz) {
            try {
                return Util.getWebApplicationContext().getBean(clazz);
            } catch (Exception e) {
                log.trace("获取BEAN失败", e);
                return null;
            }
        }

        /**
         * 从Spring获取指定名称的bean
         */
        public static Object getBean(String name) {
            try {
                return Util.getWebApplicationContext().getBean(name);
            } catch (Exception e) {
                log.trace("获取BEAN失败", e);
                return null;
            }
        }

        /**
         * 从Spring获取指定类型的指定名称bean
         */
        public static <T> T getBean(Class<T> clazz, String name) {
            try {
                return Util.getWebApplicationContext().getBean(name, clazz);
            } catch (Exception e) {
                log.trace("获取BEAN失败", e);
                return null;
            }
        }

        /**
         * 从Spring获取带有指定类型注解的bean
         */
        public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
            return getWebApplicationContext().getBeansWithAnnotation(annotationType);
        }

        /**
         * 获取指定字段
         */
        public static Field getField(Class<?> clazz, String field) {
            Field f = null;
            while (f == null && !clazz.getName().equals(Constants.CLASS_OBJECT)) {
                try {
                    f = clazz.getDeclaredField(field);
                } catch (Exception e) {
                    clazz = clazz.getSuperclass();
                }
            }
            return f;
        }

        /**
         * 获取字段
         *
         * @param type 如果type!=null则只返回本类型的字段
         */
        public static List<Field> getFields(Class<?> clazz, Class<?> type, boolean ignoreStatic, boolean ignoreTransient, String[] ignoredFields) {
            List<Field> result = new ArrayList<>();
            Set<String> fieldNames = new HashSet<>();
            Set<String> ignoredFieldList = new HashSet<>();
            if (ignoredFields != null && ignoredFields.length > 0) {
                ignoredFieldList.addAll(Arrays.asList(ignoredFields));
            }
            while (!clazz.getName().equals(Constants.CLASS_OBJECT)) {
                for (Field f : clazz.getDeclaredFields()) {
                    if (type != null && !f.getType().equals(type)) {
                        continue;
                    }
                    if (ignoreStatic && Modifier.isStatic(f.getModifiers())) {
                        continue;
                    }
                    if (ignoreTransient && f.getAnnotation(Transient.class) != null) {
                        continue;
                    }
                    if (ignoredFieldList.contains(f.getName()) || fieldNames.contains(f.getName())) {
                        continue;
                    }
                    fieldNames.add(f.getName());
                    result.add(f);
                }
                clazz = clazz.getSuperclass();
            }
            return result;
        }

        /**
         * 获取指定类型的非静态字段
         */
        public static List<Field> getFields(Class<?> clazz, Class<?> type) {
            return getFields(clazz, type, true, true, null);
        }

        /**
         * 获取非静态字段
         */
        public static List<Field> getFields(Class<?> clazz) {
            return getFields(clazz, null);
        }

        /**
         * 从类里取出带有某个指定注解的字段
         */
        public static List<Field> getFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
            List<Field> result = new ArrayList<>();
            for (Field field : getFields(clazz)) {
                if (field.getAnnotation(annotation) != null) {
                    result.add(field);
                }
            }
            return result;
        }

        /**
         * 删除最后一个字符
         */
        public static String deleteLastChar(String str) {
            if (hasText(str)) {
                return str.substring(0, str.length() - 1);
            } else {
                return str;
            }
        }

        /**
         * 删除第一个字符
         */
        public static String deleteFirstChar(String str) {
            if (hasText(str)) {
                return str.substring(1, str.length() - 1);
            } else {
                return str;
            }
        }

        /**
         * 将第一个字符转换成大写
         */
        public static String upperCaseFirstChar(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            if (str.length() == 1) {
                return str.toUpperCase();
            }
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        /**
         * 将第一个字符转换成小写
         */
        public static String lowerCaseFirstChar(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            if (str.length() == 1) {
                return str.toUpperCase();
            }
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }

        public static String toUpperCase(String str) {
            return Util.hasText(str) ? str.toUpperCase() : str;
        }

        public static String toLowerCase(String str) {
            return Util.hasText(str) ? str.toLowerCase() : str;
        }

        /**
         * 返回ExtJS用的JSON数据，多用于普通的FORM提交后的返回。
         */
        public static void json(HttpServletResponse response, boolean success, String msg) {
            Map<String, Object> model = new HashMap<>();
            model.put(Constants.SUCCESS, success);
            if (hasText(msg)) {
                model.put(Constants.MSG, msg);
            }
            try {
                Util.addCacheControlHeaders(response, true);
                response.getWriter().print(JSON.toJSONString(model));
            } catch (Exception e) {
                log.trace("输出JSON对象失败", e);
            }
        }

        /**
         * 返回ExtJS用的JSON数据，多用于普通的FORM提交后的返回。
         */
        public static void json(HttpServletResponse response, boolean success) {
            json(response, success, null);
        }

        /**
         * 返回ExtJS用的JSON数据，多用于普通的FORM提交后的返回。
         *
         * @param msg 如果msg!=null就表示操作失败。
         */
        public static void json(HttpServletResponse response, String msg) {
            json(response, !hasText(msg), msg);
        }

        /**
         * 返回ExtJS用的JSON数据，多用于普通的FORM提交后的返回。
         */
        public static void json(HttpServletResponse response, Object object) {
            try {
                Util.addCacheControlHeaders(response, true);
                response.getWriter().print(JSON.toJSONString(object));
            } catch (IOException ignored) {
                log.trace("输出JSON对象失败", ignored);
            }
        }

        /**
         * 只输出指定的字段
         */
        public static <T extends BaseEntity> void json(HttpServletResponse response, T pojo, String[] fields) {
            try {
                Util.addCacheControlHeaders(response, true);
                response.getWriter().print(JSON.toJSONString(pojo, new SimplePropertyPreFilter(pojo.getClass(), fields)));
            } catch (IOException ignored) {
                log.trace("输出JSON对象失败", ignored);
            }
        }

        /**
         * 返回ExtJS用的JSON数据，用于JsonStore的请求返回。
         */
        public static void json(HttpServletResponse response, long totalCount, Collection<?> contents) {
            Map<String, Object> model = new HashMap<>();
            model.put(Constants.SUCCESS, true);
            model.put(Constants.TOTAL, totalCount);
            model.put(Constants.LIST, contents);
            try {
                Util.addCacheControlHeaders(response, true);
                response.getWriter().print(JSON.toJSONString(model));
            } catch (Exception ignored) {
                log.trace("输出JSON对象失败", ignored);
            }
        }

        /**
         * 将列表中的对象的字段提取成一个新的列表
         */
        public static <V, K> List<V> list(List<K> list, String field) {
            List<V> result = new ArrayList<>();
            for (K k : list) {
                try {
                    result.add((V) Util.getGetter(k.getClass(), field).invoke(k));
                } catch (Exception ignored) {
                    log.trace("无法正确获取字段的值", ignored);
                }
            }
            return result;
        }

        /**
         * 将列表中的对象的字段提取成一个新的Set
         */
        public static <V, K> Set<V> set(List<K> list, String field) {
            Set<V> result = new HashSet<>();
            for (K k : list) {
                try {
                    result.add((V) Util.getGetter(k.getClass(), field).invoke(k));
                } catch (Exception ignored) {
                    log.trace("无法正确获取字段的值", ignored);
                }
            }
            return result;
        }

        /**
         * 将列表里的对象按字段值封装成Map
         */
        public static <K, V> Map<K, V> map(List<V> list, String keyField) {
            Map<K, V> map = new HashMap<>();
            for (V v : list) {
                try {
                    map.put((K) Util.getGetter(v.getClass(), keyField).invoke(v), v);
                } catch (Exception ignored) {
                    log.trace("无法正确获取字段的值", ignored);
                }
            }
            return map;
        }

        /**
         * 将对象变成Field-VALUE的MAP
         */
        public static Map<String, Object> map(Object object) {
            Map<String, Object> map = new HashMap<>();
            for (Field field : Util.getFields(object.getClass(), null, true, false, null)) {
                map.put(field.getName(), getFieldValue(object, field));
            }
            return map;
        }

        /**
         * 将HttpServletRequest的参数拼装成一个Map
         */
        public static Map<String, String> map(HttpServletRequest request) {
            Map<String, String> params = new HashMap<>();
            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                String[] values = request.getParameterValues(name);
                if (values != null) {
                    int length = values.length;
                    if (length == 1) {
                        params.put(name, values[0]);
                    } else {
                        params.put(name, Util.join(values));
                    }
                } else {
                    params.put(name, Constants.EMPTY);
                }
            }
            return params;
        }

        /**
         * 去掉Map中的空值字段
         */
        public static <K, V> Map<K, V> clearEmptyValue(Map<K, V> map) {

            Set<K> keys = new HashSet<>(map.size());
            for (K k : map.keySet()) {
                keys.add(k);
            }
            for (K k : keys) {
                V v = map.get(k);
                try {
                    if (v == null || !Util.hasText(v.toString())) {
                        map.remove(k);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return map;
        }


        /**
         * 取对象内指定字段的值
         */
        public static Object getFieldValue(Object object, Field field) {
            try {
                return Util.getGetter(object.getClass(), field.getName()).invoke(object);
            } catch (Exception e) {
                log.trace("无法正确获取字段的值", e);
                return null;
            }
        }

        /**
         * 取对象内指定字段的值
         */
        public static Object getFieldValue(Object object, String field) {
            try {
                return Util.getGetter(object.getClass(), field).invoke(object);
            } catch (Exception e) {
                log.trace("无法正确获取字段的值", e);
                return null;
            }
        }

        /**
         * 将数组封装成字符串
         */
        public static String join(String[] array, String quote) {
            if (quote == null) {
                quote = Constants.EMPTY;
            }
            if (array == null || array.length == 0) {
                return quote;
            }
            String str = Constants.EMPTY;
            for (String s : array) {
                str += quote + s + quote + Constants.COMMA;
            }

            return deleteLastChar(str);
        }

        /**
         * 将数组拼装成字符串
         */
        public static String join(String[] array) {
            return join(array, null);
        }

        /**
         * 把集合拼装成字符串
         */
        public static String join(Collection<String> set, String fix) {
            if (set == null) {
                return fix;
            } else {
                return join(set.toArray(new String[set.size()]), fix);
            }
        }

        /**
         * 把集合拼装成字符串
         */
        public static String join(Collection<String> set) {
            return join(set, null);
        }

        /**
         * 将半角字符全部转换成全角字符
         */
        public static String toSBC(String str) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == Constants.SPACE_CHAR) {
                    chars[i] = Constants.U3000;
                } else if (chars[i] < Constants.U177) {
                    chars[i] = (char) (chars[i] + 65248);
                }
            }
            return new String(chars);
        }

        /**
         * 将全角字符转换成半角字符
         */
        public static String toDBC(String str) {
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == Constants.U3000) {
                    c[i] = Constants.SPACE_CHAR;
                } else if (c[i] > Constants.UFF00 && c[i] < Constants.UFF5F) {
                    c[i] = (char) (c[i] - 65248);
                }
            }
            return new String(c);
        }

        /**
         * 判断两个对象是否一样
         */
        public static <T> boolean equals(T o1, T o2) {
            if (o1 == null) {
                return o2 == null;
            } else if (o2 == null) {
                return false;
            }
            switch (o1.getClass().getName()) {
                case "int":
                case "float":
                case "double":
                case "long":
                case "java.math.BigDecimal": {
                    String s1 = Util.formatDecimal(o1), s2 = Util.formatDecimal(o2);
                    return s1.equals(s2);
                }
            }
            return JSON.toJSONString(o1).equals(JSON.toJSONString(o2));
        }

        /**
         * 把两个同类型对象中值不相同的字段找出来
         */
        public static <T> Set<Field> findDifference(T t1, T t2) {
            Set<Field> fields = new HashSet<>();
            Class<?> clazz = t1.getClass();
            for (Field field : getFields(clazz)) {
                try {
                    Method getter = Util.getGetter(clazz, field.getName());
                    Object o1 = getter.invoke(t1), o2 = getter.invoke(t2);
                    if (!Util.equals(o1, o2)) {
                        fields.add(field);
                    }
                } catch (Exception ignored) {
                    log.trace("无法正确获取字段的值", ignored);
                }
            }
            return fields;
        }

        /**
         * 生成指定长度的随机字符串0-9 A-Z的范围
         */
        public static String randomString(int length) {
            if (length < 1) {
                return Constants.EMPTY;
            }
            Random rand = new Random();
            char[] randBuffer = new char[length];
            for (int i = 0; i < randBuffer.length; i++) {
                randBuffer[i] = Constants.AZ09[rand.nextInt(Constants.AZ09_LENGTH)];
            }
            return new String(randBuffer);
        }

        /**
         * 将两个对象进行排序比较
         */
        public static <T> int compare(T v1, T v2) {
            if (v1 == null) {
                if (v2 == null) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (v2 == null) {
                    return 1;
                } else {
                    int compared = 0;
                    if (v1.getClass().getName().equals(Constants.CLASS_STRING)) {
                        String s1 = (String) v1;
                        String s2 = (String) v2;
                        compared = Util.compareString(s1, s2);
                    } else {
                        try {
                            Comparable c1 = (Comparable) v1, c2 = (Comparable) v2;
                            compared = c1.compareTo(c2);
                        } catch (Exception ignored) {
                            log.trace("无法正确排序", ignored);
                        }
                    }
                    return compared;
                }
            }
        }


        /**
         * 比较字符串，会先检测是否出现汉字，如果出现了汉字，将采用汉语拼音排序，即 Util.comparePinYin()的结果
         * 普通字符串，先比较长度，再逐个字符比较
         */
        public static int compareString(String s1, String s2) {

            // 先判断是否为NULL，弄成空字符串
            if (s1 == null) {
                s1 = Constants.EMPTY;
            }
            if (s2 == null) {
                s2 = Constants.EMPTY;
            }

            // 先取出长度，算出长度差
            int l1 = s1.length(), l2 = s2.length(), lx = l1 - l2;

            // 检查看是否是汉字，如果是，就采用拼音排序
            for (int i = 0; i < Math.min(l1, l2); i++) {
                if (Character.isSupplementaryCodePoint(s1.charAt(i)) || Character.isSupplementaryCodePoint(s2.charAt(i))) {
                    return comparePinYin(s1, s2);
                }
            }

            for (int i = Math.min(l1, l2); i < Math.max(l1, l2); i++) {
                if (lx > 0 && Character.isSupplementaryCodePoint(s1.charAt(i))) {
                    return 1;
                } else if (lx < 0 && Character.isSupplementaryCodePoint(s2.charAt(i))) {
                    return -1;
                }
            }

            // 不是汉字，长度不一样，就按照长度来排序
            if (lx != 0) {
                return lx;
            }

            // 长度一样，每个字符来排序
            for (int i = 0; i < s1.length(); i++) {
                int x = s1.charAt(i) - s2.charAt(i);
                if (x != 0) {
                    return x;
                }
            }

            // 最终的结果，是字符串一样的
            return 0;
        }


        public static int comparePinYin(String s1, String s2) {

            for (int i = 0; i < s1.length() && i < s2.length(); i++) {

                int codePoint1 = s1.charAt(i);
                int codePoint2 = s2.charAt(i);

                if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
                    i++;
                }
                if (codePoint1 != codePoint2) {
                    if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
                        return codePoint1 - codePoint2;
                    }
                    // TODO
                    /*String pinyin1 = Util.getPinYin((char) codePoint1);
                    String pinyin2 = Util.getPinYin((char) codePoint2);
                    if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
                        if (!pinyin1.equals(pinyin2)) {
                            return pinyin1.compareTo(pinyin2);
                        }
                    } else {
                        return codePoint1 - codePoint2;
                    }*/
                }
            }
            return s1.length() - s2.length();
        }


        /**
         * 按照字段排序
         */
        public static <T> List<T> sort(final List<T> list, final String field, final String dir) {
            return sort(list, field, Util.parseEnumByName(Dir.class, dir));
        }

        /**
         * 把列表内的对象排序
         *
         * @param list  被排序的列表
         * @param field 列表内对象的排序字段名称
         * @param dir   DESC or ASC
         */
        public static <T> List<T> sort(final List<T> list, final String field, final Dir dir) {
            final boolean asc = dir == null || dir == Dir.ASC;
            Collections.sort(list, new Comparator<T>() {
                @SuppressWarnings("ConstantConditions")
                public int compare(T o1, T o2) {
                    Object v1 = null, v2 = null;
                    if (o1 instanceof Map) {
                        try {
                            Method getter = Map.class.getMethod(Constants.GET, Object.class);
                            v1 = getter.invoke(o1, field);
                            v2 = getter.invoke(o2, field);
                        } catch (Exception ignored) {
                            log.trace("无法正确获取字段的值", ignored);
                        }
                    } else {
                        Method getter = Util.getGetter(o1.getClass(), field);
                        try {
                            v1 = getter.invoke(o1);
                            v2 = getter.invoke(o2);
                        } catch (Exception ignored) {
                            log.trace("无法正确获取字段的值", ignored);
                        }
                    }
                    int compared = Util.compare(v1, v2);
                    if (compared == 0 && !Constants.CREATE_TIME.equals(field)) {
                        Method getter = null;
                        try {
                            getter = o1.getClass().getMethod(Constants.GET_CREATE_TIME);
                        } catch (Exception ignored) {
                            log.trace("无法正确获取Getter", ignored);
                        }
                        try {
                            v1 = getter.invoke(o1);
                            v2 = getter.invoke(o2);
                        } catch (Exception ignored) {
                            log.trace("无法正确获取字段的值", ignored);
                        }
                        compared = Util.compare(v1, v2);
                    }
                    return asc ? compared : -compared;
                }
            });
            return list;
        }

        /**
         * 调用URL请求，采用UTF-8编码
         */
        public static String openURL(String url) {
            return openURL(url, new HashMap<String, Object>(), Constants.UTF_8, true);
        }

        /**
         * 调用URL请求，对参数采用UT-8编码进行编码
         */
        public static <V> String openURL(String url, Map<String, V> params) {
            return openURL(url, params, Constants.UTF_8, true);
        }

        /**
         * 调用URL请求
         */
        public static <V> String openURL(String url, Map<String, V> params, boolean encode) {
            return openURL(url, params, Constants.UTF_8, encode);
        }


        /**
         * 调用URL请求
         */
        public static <V> String openURL(String url, Map<String, V> params, String charset) {
            return openURL(url, params, charset, true);
        }

        /**
         * 调用URL请求
         *
         * @param url     要访问的URL地址
         * @param params  传递的参数列表
         * @param charset 数据编码，默认UTF-8
         * @param encode  是否需要URLEncode编码
         */
        public static <V> String openURL(String url, Map<String, V> params, String charset, boolean encode) {
            if (!hasText(charset)) {
                charset = Constants.UTF_8;
            }
            try {
                String query = generateUrlQueryString(params, charset, encode);

                log.info("打开URL：" + url + "，参数：" + query);

                URLConnection conn = new URL(url).openConnection();
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty(Constants.ACCEPT, "text/xml;text/html");
                conn.setRequestProperty(Constants.ACCEPT_CHARSET, charset);
                conn.setRequestProperty(Constants.CONTENT_LENGTH, Integer.toString(query.length()));
                conn.setRequestProperty(Constants.CONTENT_TYPE, Constants.APPLICATION_X_WWW_FORM_URLENCODED + charset);
                conn.setRequestProperty(Constants.HOST, conn.getURL().getHost());
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
                writer.write(query);
                writer.flush();
                writer.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(Constants.NEW_LINE);
                }
                reader.close();
                return deleteLastChar(result.toString());
            } catch (Exception e) {
                log.trace("无法正确获取URL内容", e);
                String msg = e.getMessage();
                return "出现异常：" + msg;
            }
        }

        /**
         * 获取指定软件包下的所有类
         */
        public static List<Class<?>> getClasses(String packageName) {
            List<Class<?>> classes = new ArrayList<>();
            String packageDirName = packageName.replaceAll("\\.", Constants.SLASH);
            try {
                Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
                while (resources.hasMoreElements()) {
                    URL url = resources.nextElement();
                    if ("jar".equals(url.getProtocol())) { // 打包来的jar文件
                        JarFile jarFile;
                        try {
                            JarURLConnection conn = (JarURLConnection) url.openConnection();
                            jarFile = conn.getJarFile();
                        } catch (IOException e) {
                            continue;
                        }
                        for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements(); ) {
                            JarEntry entry = entries.nextElement();
                            String entryName = entry.getName();
                            if (entryName.startsWith(packageDirName) && entryName.endsWith(Constants.DOT_CLASS) && !entryName.contains(Constants.DOLLAR)) {
                                String className = entryName.replace(Constants.SLASH, Constants.DOT).substring(0, entryName.length() - Constants.DOT_CLASS.length());
                                try {
                                    classes.add(Class.forName(className));
                                } catch (ClassNotFoundException ignored) {
                                    log.trace("无法正确获取Class", ignored);
                                }
                            }
                        }
                    } else if ("file".equals(url.getProtocol())) { // 普通散装的class文件夹
                        String filePath;
                        try {
                            filePath = URLDecoder.decode(url.getFile(), Constants.UTF_8);
                        } catch (UnsupportedEncodingException e) {
                            log.trace("无法正确获取文件，不支持的编码", e);
                            continue;
                        }
                        getClassesFromDirectory(packageName, new File(filePath), classes);
                    }
                }
            } catch (IOException ignored) {
                log.trace("无法正确获取Class，出现IO异常", ignored);
            }
            return classes;
        }


        /**
         * 判断字符串是否为手机号码
         */
        public static boolean isPhoneNumber(String number) {
            try {
                return !(number == null || number.length() != 11) && number.startsWith(Constants.ONE) && Pattern.compile(Constants.MOBILE_PHONE_PATTERN).matcher(number).matches();
            } catch (Exception e) {
                return false;
            }
        }

        /**
         * 判断是否是Email格式的字符串
         */
        public static boolean isEmail(String email) {
            return hasText(email) && Pattern.compile(Constants.EMAIL_PATTERN).matcher(email.toLowerCase()).matches();
        }

        /**
         * 将金额数据转换成财务大写
         */
        public static String toRMB(String fee) {
            String num = "零壹贰叁肆伍陆柒捌玖";
            String dw = "圆拾佰仟万亿";
            String mm[] = fee.split("/.");
            String money = mm[0];
            String result = num.charAt(Integer.parseInt("" + mm[1].charAt(0))) + "角" + num.charAt(Integer.parseInt("" + mm[1].charAt(1))) + "分";
            for (int i = 0; i < money.length(); i++) {
                String str = "";
                int n = Integer.parseInt(money.substring(money.length() - i - 1, money.length() - i));
                str = str + num.charAt(n);
                if (i == 0) {
                    str = str + dw.charAt(i);
                } else if ((i + 4) % 8 == 0) {
                    str = str + dw.charAt(4);
                } else if (i % 8 == 0) {
                    str = str + dw.charAt(5);
                } else {
                    str = str + dw.charAt(i % 4);
                }
                result = str + result;
            }
            result = result.replaceAll("零([^亿万圆角分])", "零");
            result = result.replaceAll("亿零+万", "亿零");
            result = result.replaceAll("零+", "零");
            result = result.replaceAll("零([亿万圆])", "$1");
            result = result.replaceAll("壹拾", "拾");
            return result;
        }

        /**
         * 从rquest参数中取name的值并解析成JSONObject对象的列表
         */
        public static List<JSONObject> parseJson(HttpServletRequest request, String name) {
            List<JSONObject> items = new ArrayList<>();
            try {
                JSONArray array = JSONArray.parseArray(request.getParameter(name));
                for (int i = 0, j = array.size(); i < j; i++) {
                    items.add(array.getJSONObject(i));
                }
            } catch (Exception ignored) {
                log.debug("解析JSON数据失败", ignored);
            }
            return items;
        }

        /**
         * 从request参数中取name的值并解析成JSONObject对象
         */
        public static JSONObject parseJSONObject(HttpServletRequest request, String name) {
            return JSONObject.parseObject(request.getParameter(name));
        }

        /**
         * 从rquest参数中取name的值并解析成指定clazz类型的对象的列表
         */
        public static <T> List<T> parseJson(HttpServletRequest request, String name, Class<T> clazz) {
            List<T> items = new ArrayList<>();
            try {
                JSONArray array = JSONArray.parseArray(request.getParameter(name));
                for (int i = 0, j = array.size(); i < j; i++) {
                    items.add(array.getObject(i, clazz));
                }
            } catch (Exception ignored) {
                log.debug("解析JSON数据失败", ignored);
            }
            return items;
        }

        /**
         * 内部辅助方法，从文件夹中读取类
         */
        private static void getClassesFromDirectory(String packageName, File directory, List<Class<?>> classes) {
            if (!directory.exists() || !directory.isDirectory()) {
                return;
            }
            File[] files = directory.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return (pathname.isDirectory() || (pathname.getName().endsWith(Constants.DOT_CLASS) && !pathname.getName().contains(Constants.DOLLAR)));
                }
            });
            for (File file : files) {
                if (file.isDirectory()) {
                    getClassesFromDirectory(packageName + Constants.DOT + file.getName(), file, classes);
                } else {
                    String className = packageName + Constants.DOT + file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException ignored) {
                        log.trace("无法正确获取类", ignored);
                    }
                }
            }
        }


        /**
         * 内部辅助方法，把参数添加到URL地址上
         */
        private static void appendParamToUrlQuery(StringBuffer query, String key, String value, String charset, boolean encode) {
            if (Util.hasText(key)) {
                query.append(key);
                query.append(Constants.EQUALS);
            }

            if (!Util.hasText(charset)) {
                charset = Constants.UTF_8;
            }

            value = value.replaceAll(Constants.NEW_LINE, Constants.EMPTY).replaceAll(Constants.SPACE, Constants.EMPTY);

            if (encode) {
                try {
                    value = URLEncoder.encode(value, charset);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("无法对参数进行URL编码，遇到了不支持的编码：" + e.getMessage(), e);
                }
            }

            query.append(value);
            query.append(Constants.AND);
        }


        /**
         * 将参数对生成为URL的GET请求的参数字符串
         */
        public static <V> String generateUrlQueryString(Map<String, V> params) {
            return generateUrlQueryString(params, Constants.UTF_8, true);
        }

        /**
         * 将参数以指定编码生成为URL的字符串形式
         */
        public static <V> String generateUrlQueryString(Map<String, V> params, String charset) {
            return generateUrlQueryString(params, charset, true);
        }


        /**
         * 把参数列表拼装成URL字符串
         */
        public static <V> String generateUrlQueryString(Map<String, V> params, String charset, boolean encode) {
            if (params == null || params.size() == 0) {
                return Constants.EMPTY;
            }
            StringBuffer query = new StringBuffer();
            for (Map.Entry<String, V> param : params.entrySet()) {
                String key = param.getKey();
                Object value = param.getValue();
                if (value == null) {
                    value = Constants.EMPTY;
                }
                if (value.getClass().equals(HashSet.class)) {
                    for (Object object : ((HashSet<Object>) value).toArray()) {
                        appendParamToUrlQuery(query, key, object.toString(), charset, encode);
                    }
                } else {
                    appendParamToUrlQuery(query, key, value.toString(), charset, encode);
                }
            }
            if (query.length() > 0) {
                query = query.deleteCharAt(query.length() - 1);
            }
            return query.toString();
        }

        /**
         * 获取当前的HttpServletRequest
         */
        public static HttpServletRequest getHttpServletRequest() {
            try {
                return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            } catch (Exception e) {
                log.trace("无法正确获取HttpServletRequest对象", e);
                return null;
            }
        }


        /**
         * 获取当前请求的URI
         */
        public static String getRequestURI() {
            return getHttpServletRequest().getRequestURI();
        }

        /**
         * 通配符匹配的实现
         *
         * @param pattern 匹配规则，只支持用星号(*)作为通配符
         * @param str     要检测是否匹配的字符串
         */
        public static boolean match(String pattern, String str) {
            if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(str)) {
                return false;
            }
            final boolean startWith = pattern.startsWith(Constants.STAR);
            final boolean endWith = pattern.endsWith(Constants.STAR);
            String[] array = StringUtils.split(pattern, Constants.STAR);
            int currentIndex;
            int lastIndex = -1;
            switch (array.length) {
                case 0:
                    return true;
                case 1:
                    currentIndex = str.indexOf(array[0]);
                    if (startWith && endWith) {
                        return currentIndex >= 0;
                    }
                    if (startWith) {
                        return currentIndex + array[0].length() == str.length();
                    }
                    if (endWith) {
                        return currentIndex == 0;
                    }
                    return str.equals(pattern);
                default:
                    for (String part : array) {
                        if (StringUtils.isNoneBlank(part)) {
                            currentIndex = str.indexOf(part);
                            if (currentIndex > lastIndex) {
                                lastIndex = currentIndex;
                                continue;
                            }
                            return false;
                        }
                    }
                    return true;
            }
        }

        /**
         * 生成UUID字符串
         */
        public static String uuid() {
            return UUID.randomUUID().toString();
        }

        /**
         * 任意个数值相加，这里的数值都会按照第一个参数的类型来转换类型
         */
        public static <T> T add(T... objects) {
            if (objects == null || objects.length == 0) {
                return null;
            }

            // 取出第一个不为null的数据
            T notNull = null;
            for (T object : objects) {
                if ((notNull = object) != null) {
                    break;
                }
            }
            // 没取到，就返回了。
            if (notNull == null) {
                return null;
            }

            // 判断数据的类型，再来相加。
            final String className = notNull.getClass().getName();
            switch (className) {
                case "int":
                case "java.lang.Integer": {
                    Integer sum = 0;
                    for (Object object : objects) {
                        if (object != null) {
                            sum += (Integer) object;
                        }
                    }
                    return (T) sum;
                }
                case "long":
                case "java.lang.Long": {
                    Long sum = 0L;
                    for (Object object : objects) {
                        if (object != null) {
                            sum += (Long) object;
                        }
                    }
                    return (T) sum;
                }
                case "double":
                case "java.lang.Double": {
                    Double sum = 0D;
                    for (Object object : objects) {
                        if (object != null) {
                            sum += (Double) object;
                        }
                    }
                    return (T) sum;
                }
                case "java.math.BigDecimal": {
                    BigDecimal sum = Constants.BIGDECIMAL_ZERO;
                    for (Object object : objects) {
                        if (object != null) {
                            sum = sum.add((BigDecimal) object);
                        }
                    }
                    return (T) sum;
                }
                case "java.math.BigInteger": {
                    BigInteger sum = BigInteger.ZERO;
                    for (Object object : objects) {
                        if (object != null) {
                            sum = sum.add((BigInteger) object);
                        }
                    }
                    return (T) sum;
                }
                default: {
                    throw new RuntimeException("不支持的累加类型：" + className);
                }
            }
        }


        /**
         * 任意个数相乘，如果没有实际的值就返回0，否则返回相乘的结果
         */
        public static BigDecimal multiply(BigDecimal... bigDecimals) {
            boolean hasValue = false;
            BigDecimal result = Constants.BIGDECIMAL_ONE;
            for (BigDecimal bigDecimal : bigDecimals) {
                if (bigDecimal != null) {
                    result = result.multiply(bigDecimal);
                    hasValue = true;
                }
            }
            return hasValue ? result : Constants.BIGDECIMAL_ZERO;
        }

        /**
         * 任意个数相减
         *
         * @param minuend   被减数
         * @param subtracts 减数
         */
        public static BigDecimal subtract(BigDecimal minuend, BigDecimal... subtracts) {
            if (minuend == null) {
                minuend = Constants.BIGDECIMAL_ZERO;
            }
            for (BigDecimal subtract : subtracts) {
                if (Util.hasValue(subtract)) {
                    minuend = minuend.subtract(subtract);
                }
            }
            return minuend;
        }

        /**
         * 任意个数相除
         *
         * @param division  被除数
         * @param scale     要保留的小数位数
         * @param roundMode 小数位保留算法，为BigDecimal.ROUND_XXX的常量
         * @param divisors  除数
         */
        public static BigDecimal divide(BigDecimal division, int scale, int roundMode, BigDecimal... divisors) {
            if (!Util.hasValue(division)) {
                return Constants.BIGDECIMAL_ZERO;
            }
            for (BigDecimal divisor : divisors) {
                if (divisor == null) {
                    divisor = Constants.BIGDECIMAL_ONE;
                }
                division = division.divide(divisor, scale, roundMode);
            }
            return division;
        }

        /**
         * 任意个数相除，默认采用四舍六入五成双的算法，保留两位小数。
         */
        public static BigDecimal divide(BigDecimal division, BigDecimal... divisors) {
            return divide(division, 2, BigDecimal.ROUND_HALF_EVEN, divisors);
        }

        /**
         * 将文件大小的字符串表达形式转换成字节数
         */
        public static long getFileSize(String fileSize) {
            fileSize = fileSize.toLowerCase();
            try {
                if (fileSize.endsWith("b")) {
                    fileSize = deleteLastChar(fileSize);
                }
                if (fileSize.endsWith("byte")) {
                    return Long.parseLong(fileSize.substring(0, fileSize.length() - 4));
                } else if (fileSize.endsWith("k")) {
                    return Long.parseLong(fileSize.substring(0, fileSize.length() - 1)) * 1024;
                } else if (fileSize.endsWith("m")) {
                    return Long.parseLong(fileSize.substring(0, fileSize.length() - 1)) * 1024 * 1024;
                } else if (fileSize.endsWith("g")) {
                    return Long.parseLong(fileSize.substring(0, fileSize.length() - 1)) * 1024 * 1024 * 1024;
                } else if (fileSize.endsWith("t")) {
                    return Long.parseLong(fileSize.substring(0, fileSize.length() - 1)) * 1024 * 1024 * 1024 * 1024;
                }
            } catch (Exception ignored) {
                log.trace("无法正确解析字符串成文件大小", ignored);
            }
            return 0;
        }

        /**
         * 将值转换成文件体积的字符串表达式
         */
        public static String getFileSize(long fileSize) {
            if (fileSize < 1024) {
                return fileSize + "Byte";
            } else if (fileSize < 1048576) {
                return new BigDecimal(fileSize).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_EVEN) + "KB";
            } else if (fileSize < 1073741824) {
                BigDecimal divider = new BigDecimal(1024).multiply(new BigDecimal(1024));
                return new BigDecimal(fileSize).divide(divider, 2, BigDecimal.ROUND_HALF_EVEN) + "MB";
            } else if (fileSize < 1099511627776L) {
                BigDecimal divider = new BigDecimal(1024).multiply(new BigDecimal(1024)).multiply(new BigDecimal(1024));
                return new BigDecimal(fileSize).divide(divider, 2, BigDecimal.ROUND_HALF_EVEN) + "GB";
            } else {
                BigDecimal divider = new BigDecimal(1024).multiply(new BigDecimal(1024)).multiply(new BigDecimal(1024)).multiply(new BigDecimal(1024));
                return new BigDecimal(fileSize).divide(divider, 2, BigDecimal.ROUND_HALF_EVEN) + "TB";
            }
        }

        /**
         * 获取传入文件的扩展名
         */
        public static String getFileExtension(String fileName) {
            try {
                return fileName.substring(fileName.lastIndexOf(Constants.DOT) + 1);
            } catch (Exception e) {
                log.trace("无法正确获取文件扩展名", e);
                return Constants.EMPTY;
            }
        }

        /**
         * 获取传入文件的扩展名
         */
        public static String getFileExtension(File file) {
            try {
                return getFileExtension(file.getName());
            } catch (Exception e) {
                log.trace("无法正确获取文件扩展名", e);
                return Constants.EMPTY;
            }
        }

        /**
         * 从HttpServletRequest中获取要上传的文件
         */
        public static MultipartFile getMultipartFile(HttpServletRequest request, String fieldName) {
            try {
                return ((MultipartHttpServletRequest) request).getFile(fieldName);
            } catch (Exception e) {
                throw new RuntimeException("取MultipartFile失败，请确认以下几点：\n1、dispatcher-servlet.xml中有如下配置：<bean id=\"multipartResolver\" class=\"org.springframework.web.multipart.commons.CommonsMultipartResolver\"/>\n2、提交文件的form有如下的配置：enctype=\"multipart/form-data\"\n3、传来的request必须是在Controller里由Spring自动拼装出来的HttpServletRequest，不建议使用Util.getHttpServletRequest()来获取request，因为有可能后者获取到的是经CAS包装过的request", e);
            }
        }

        /**
         * 在JSON返回值上设置浏览器的头信息，主要是禁用浏览器的缓存功能
         *
         * @param response     要设置缓存头信息的返回值
         * @param responseJson 是否返回JSON内容，是则contentType设置为application/json 否则设置为text/html
         */
        public static HttpServletResponse addCacheControlHeaders(HttpServletResponse response, boolean responseJson) {
            if (!Util.hasText(response.getContentType())) {
                response.setContentType(responseJson ? "application/json" : "text/html");
            }
            response.setCharacterEncoding(Constants.UTF_8);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragrma", "no-cache");
            response.setDateHeader("Expires", 0);
            return response;
        }


        /**
         * 取字符串的拼音首字母
         */
        public static String getPinYin(String str) {
            return getPinYin(str, false);
        }

        /**
         * TODO 取字符串的拼音
         */
        public static String getPinYin(String str, boolean full) {
            if (!Util.hasText(str)) {
                return Constants.EMPTY;
            }
            StringBuffer sb = new StringBuffer();
            char[] cs = str.toCharArray();

            return sb.toString().replaceAll("\\W", "").trim();
        }


        /**
         * 获取某个指定URI的服务器绝对路径
         */
        public static String getRealPath(String uri) {
            return Util.getServletContext().getRealPath("") + "/" + uri;
        }

        /**
         * 从Map内取出指定key的值，如果没有这个key，则将def插入到Map并返回def
         */
        public static <K, V> V getFromMap(Map<K, V> map, K key, V def) {
            if (!map.containsKey(key)) {
                map.put(key, def);
            }
            return map.get(key);
        }

        /**
         * 将Map的结果叠加
         */
        public static <T> Map<String, Object> sumMap(Collection<T> list, String[] sumFields) {
            Map<String, Object> sumMap = new HashMap<>();
            if (list == null || list.size() == 0 || sumFields == null || sumFields.length == 0) {
                return sumMap;
            }
            boolean isMap = false;
            for (Class inf : list.iterator().next().getClass().getInterfaces()) {
                if (inf == Map.class) {
                    isMap = true;
                    break;
                }
            }
            if (!isMap) {
                return sumMap;
            }
            for (T t : list) {
                Map map = (Map) t;
                for (String field : sumFields) {
                    sumMap.put(field, Util.add(map.get(field), sumMap.get(field)));
                }
            }
            return sumMap;
        }

        /**
         * 用新的BigDecimal值替换旧的BigDecimal值
         */
        public static <T> T replaceBigDecimals(T object, BigDecimal original, BigDecimal target) {

            if (object instanceof Collection) {
                for (Object element : (Collection) object) {
                    Class<?> clazz = element.getClass();
                    for (Field field : Util.getFields(clazz, BigDecimal.class, true, false, null)) {
                        try {
                            if (Util.equals(Util.getGetter(clazz, field.getName()).invoke(element), original)) {
                                Util.getSetter(clazz, field.getName()).invoke(element, target);
                            }
                        } catch (Exception e) {
                            log.trace("无法正确获取字段的值", e);
                        }
                    }
                }
            } else if (object instanceof Map) {
                for (Map.Entry entry : ((Map<?, ?>) object).entrySet()) {
                    if (Util.equals(entry.getValue(), original)) {
                        entry.setValue(target);
                    }
                }
            } else {
                Class<?> clazz = object.getClass();
                for (Field field : Util.getFields(clazz, BigDecimal.class)) {
                    try {
                        if (Util.equals(Util.getGetter(clazz, field.getName()).invoke(object), original)) {
                            Util.getSetter(clazz, field.getName()).invoke(object, target);
                        }
                    } catch (Exception e) {
                        log.trace("无法正确获取字段的值", e);
                    }
                }
            }

            return object;
        }

        /**
         * 将异常的堆栈跟踪信息打印成字符串
         */
        public static String printStackTrace(Throwable throwable) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer, true));
            return writer.toString().replaceAll("\n", "<br>");
        }

        /**
         * 获取当前服务器的根URL
         */
        public static String getBaseUrl() {
            HttpServletRequest request = Util.getHttpServletRequest();
            return getBaseUrl(request);
        }

        /**
         * 取指定请求的根URL
         */
        public static String getBaseUrl(HttpServletRequest request) {
            return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath();
        }

        /**
         * 去掉字符串两端的空格
         */
        public static String trim(String str) {
            if (str != null && str.length() > 0) {
                str = str.trim();
            }
            return str;
        }

        /**
         * 生成自动提交的表单的HTML源码
         */
        public static <V> void autoSubmitForm(HttpServletResponse response, String url, Map<String, V> params) {

            // 生成完整的HTML代码
            StringBuilder html = new StringBuilder();
            String formId = Util.randomString(4) + "_f";
            html.append("<script language=\"javascript\">window.onload=function(){document.").append(formId).append(".submit();}</script>");
            html.append("<form id=\"").append(formId).append("\" name=\"").append(formId).append("\" action=\"").append(url).append("\" method=\"post\">");
            for (Map.Entry<String, V> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                html.append("<input type=\"hidden\" name=\"").append(key).append("\" id=\"").append(key).append("\" value=\"").append(value).append("\"/>");
            }
            html.append("</form>");

            // 设置头信息，输出HTML代码
            Util.addCacheControlHeaders(response, false);
            try {
                response.getWriter().println(html.toString());
            } catch (IOException e) {
                throw new RuntimeException("自动提交表单失败", e);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }


        /**
         * 判断当前线程的请求是否为AJAX请求
         */
        public static boolean isAjax() {
            return isAjax(getHttpServletRequest());
        }


        /**
         * 判断指定的请求是否为AJAX请求
         */
        public static boolean isAjax(HttpServletRequest request) {
            try {
                return Util.equals(Constants.XML_HTTP_REQUEST, request.getHeader(Constants.X_REQUESTED_WITH));
            } catch (Exception e) {
                return false;
            }
        }


        /**
         * 拆分字符串
         */
        public static String[] split(String str, String regex) {
            if (!Util.hasText(str)) {
                return new String[]{};
            } else {
                return str.split(regex);
            }
        }


        /**
         * 计算GPS坐标附近距离的坐标偏移量
         *
         * @param srcLatitude 原点的纬度
         * @param meters      距离（米）
         */
        public static Map<String, BigDecimal> getGpsOffset(BigDecimal srcLatitude, int meters) {

            // 纬度相差1度，距离相差111km； 精度相差1度，距离相差111cos(srcLatitude*PI/180)km
            BigDecimal unit = new BigDecimal(111);
            BigDecimal km = new BigDecimal(meters).divide(new BigDecimal(1000), 6, BigDecimal.ROUND_HALF_UP);
            BigDecimal latOffset = km.divide(unit, 6, BigDecimal.ROUND_HALF_UP);

            double div = Math.cos(srcLatitude.multiply(new BigDecimal(Math.PI).divide(new BigDecimal(180), 6, BigDecimal.ROUND_HALF_UP)).doubleValue());
            BigDecimal lngOffset = km.divide(unit.multiply(new BigDecimal(div)), 6, BigDecimal.ROUND_HALF_UP);

            java.util.Map<String, BigDecimal> result = new HashMap<>();
            result.put("latOffset", latOffset);
            result.put("lngOffset", lngOffset);

            return result;
        }


        /**
         * 计算两个GPS坐标之间的直线距离
         */
        public static double getGpsDistance(BigDecimal lat1, BigDecimal lng1, BigDecimal lat2, BigDecimal lng2) {
            BigDecimal lat = Util.add(lat1, lat2).divide(new BigDecimal(2), 6, BigDecimal.ROUND_HALF_UP); // 纬度平均值
            BigDecimal offsetLatMeter = Util.subtract(lat1, lat2).abs().multiply(new BigDecimal(111000));
            BigDecimal offsetLngMeter = Util.subtract(lng1, lng2).abs().multiply(new BigDecimal(111000)).multiply(new BigDecimal(Math.cos(lat.multiply(new BigDecimal(Math.PI).divide(new BigDecimal(180), 6, BigDecimal.ROUND_HALF_UP)).doubleValue())));
            return Math.sqrt(Util.multiply(offsetLatMeter, offsetLatMeter).add(Util.multiply(offsetLngMeter, offsetLngMeter)).doubleValue());
        }


        /**
         * 帮当前类以一个对象的形式暴露出去
         */
        public static Util instance() {
            return __instance__;
        }

    /**
     * 生成交流流水号(时间format+随机数)
     * @return
     */
    public static String generateSerialNumber() {
        return formatDate(new Date(), Constants.YYYYMMDDHHMMSS) + generateValidateCode();
    }
}

