package com.mgj.base;

import java.math.BigDecimal;

/**
 * Created by yanqu on 2016/6/14.
 */
public interface Constants {
    String BLANK_SPACE = " ";
    String SEMICOLON = ";";
    String UNDERLINE = "_";
    String NEWLINE = "\r\n";
    String UFT_8 = "UTF-8";
    String GBK = "GBK";
    String YYYY_MM_DD = "yyyy-MM-dd";
    String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


    // 万金油常量，随处都可以用到

    /**
     * 空字符串
     */
    String EMPTY = "";

    /**
     * 星号
     */
    String STAR = "*";

    /**
     * 空格
     */
    String SPACE = " ";

    /**
     * 换行符\n
     */
    String NEW_LINE = "\n";

    // 加密，解密相关的常量

    /**
     * 字符串“AES”
     */
    String AES = "AES";

    /**
     * 字符串“SHA1PRNG”
     */
    String SHA1PRNG = "SHA1PRNG";

    /**
     * 字符串“MD5”
     */
    String MD5 = "MD5";

    // 做半角和全角转换时用到

    /**
     * 空格字符
     */
    char SPACE_CHAR = ' ';

    char U3000 = '\u3000';

    char U177 = '\177';

    char UFF00 = '\uFF00';

    char UFF5F = '\uFF5F';

    // 取客户端IP地址时用到
    String X_REAL_FORWARDED_FOR = "x-real-forwarded-for";

    String X_FORWARDED_FOR = "x-forwarded-for";

    String UNKNOWN = "unknown";

    // 反射调用时用到
    String GET = "get";

    String SET = "set";

    String IS = "is";

    // 拼接URL时用到
    String AND = "&";

    String EQUALS = "=";

    String MINUS = "-";

    String SLASH = "/";

    String AT = "@";

    // 涉及到字符集、乱码、编码时都需要用到
    String UTF_8 = "utf-8";

    // 格式化日期的时候用到
    String YMD = "yyyyMMdd";

    String YM = "yyyyMM";

    String YYYY = "yyyy";

    String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

    String Y_M_D = "yyyy-MM-dd";

    String Y_M = "yyyy-MM";

    String M_D = "MM-dd";

    String START_YMD = "startYmd";

    String END_YMD = "endYmd";

    /**
     * 农历的月份
     */
    String[] LUNAR_MONTHS = {"", "正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};

    /**
     * 农历的号数
     */
    String[] LUNAR_DAYS = {"", "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十", "卅一"};

    // 给成员变量定义初始值时常用到
    BigDecimal BIGDECIMAL_ZERO = BigDecimal.ZERO;

    BigDecimal BIGDECIMAL_ONE = BigDecimal.ONE;

    BigDecimal BIGDECIMAL_HUNDRED = new BigDecimal(100);

    // 多用于从Excel里取值
    String COMMA = ",";

    String COMMA_CHINESE = "，";

    String DOT = ".";

    String E = "E";

    String DOT_ZERO = ".0";

    String DOT_CLASS = ".class";

    String DOLLAR = "$";

    // 写成ExtJS需要的JSON数据时用到
    String TRUE = "true";

    String FALSE = "false";

    String SUCCESS = "success";

    String TOTAL = "total";

    String LIST = "list";

    String MSG = "msg";

    // 做类型的switch时用到
    String CLASS_OBJECT = "java.lang.Object";

    String CLASS_STRING = "java.lang.String";

    // 生成随机密码是用到
    char[] AZ09 = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    int AZ09_LENGTH = AZ09.length - 1;

    String CREATE_TIME = "creatateTime";

    String GET_CREATE_TIME = "getCreateTime";

    String SELECT = "select";

    String ORDER_BY = "order by";

    String SELECT_COUNT = "select count";

    String SELECT_STAR = "select *";

    String SELECT_COUNT_ONE = "select count(1) ";

    String FROM = "from";

    String CONVERT = "convert", USING_GBK = " using GBK)";

    String Id = "Id", Ids = "Ids", LEFT_BRACKET = "(";

    String NLSSORT = "nlssort(", NLS_SORT = "，'NLS_SORT=SCHINESE_PINYIN_M')";

    String ONE = "1";

    String MOBILE_PHONE_PATTERN = "^[0123456789]+$";

    String EMAIL_PATTERN = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    String CONTENT_LENGTH = "Content-Length";

    String CONTENT_TYPE = "Content-Type";

    String ACCEPT = "Accept";


    String ACCEPT_CHARSET = "Accept-Charset";

    String HOST = "HOST";

    String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=";

    String XML_HTTP_REQUEST = "XMLHttpRequest";

    String X_REQUESTED_WITH = "X-Requested-With";

}
