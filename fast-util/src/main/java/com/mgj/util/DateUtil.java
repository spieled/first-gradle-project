package com.mgj.util;
import com.mgj.base.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/14.
 */
public class DateUtil {
    public static final DateFormat defaultDateFormat = new SimpleDateFormat(Constants.YYYY_MM_DD_HH_MM_SS);

    /**
     * 格式化当前时间为 yyyy-MM-dd HH:mm:ss 格式
     * @return 格式化的日期字符串
     */
    public static final String format() {
        return defaultDateFormat.format(new Date());
    }

    /**
     * 格式化当前时间为 指定 格式
     * @return 格式化的日期字符串
     */
    public static final String format(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

}
