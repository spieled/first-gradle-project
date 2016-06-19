package com.mgj.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/14.
 */
public class Constants {
    public static final String EMPTY = "";
    public static final String BLANK_SPACE = " ";
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
    public static final String UNDERLINE = "_";
    public static final String UFT_8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat(YYYY_MM_DD).format(new Date()));
        System.out.println(new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(new Date()));
    }

}
