package com.mgj.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by yanqu on 2016/6/24.
 */
public class UtilTest {

    @Test
    public void randomMobile() throws Exception {
        String mobile = Util.randomMobile();
        Assert.assertNotNull("mobile should not be null", mobile);
        Assert.assertEquals("mobile length should be 11", 11, mobile.length());
        Assert.assertEquals("mobile should start with 1", "1", mobile.substring(0,1));

    }

    @Test
    public void decodeBase64ToImage() throws Exception {

    }

    @Test
    public void getIpAddr() throws Exception {

    }

}