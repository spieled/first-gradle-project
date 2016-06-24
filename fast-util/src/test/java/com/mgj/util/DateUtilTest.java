package com.mgj.util;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yanqu on 2016/6/15.
 */
public class DateUtilTest {
    @Test
    public void format1() throws Exception {
        String str = DateUtil.format("yyyy-MM-dd");
        Assert.assertEquals(10, str.length());
    }

    @Test
    public void format() throws Exception {
        String str = DateUtil.format();
        Assert.assertEquals("string length not valid", 19, str.length());
    }

}