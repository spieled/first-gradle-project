package com.mgj.util;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by yanqu on 2016/6/24.
 */
public class UtilTest {

    @Test
    public void randomMobile() throws Exception {
        String mobile = Util.randomMobile();
        assertNotNull("mobile should not be null", mobile);
        assertEquals("mobile length should be 11", 11, mobile.length());
        assertEquals("mobile should start with 1", "1", mobile.substring(0,1));

    }

    @Test
    public void decodeBase64ToImage() throws Exception {

    }

    @Test
    public void getIpAddr() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        final String ip = "192.168.2.2";
        when(request.getHeader("x-forwarded-for")).thenReturn(ip);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(ip);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(ip);

        String ipAddr = Util.getIpAddr(request);
        assertNotNull(ipAddr);
        assertEquals("ip should be " + ip, ip, ipAddr);

    }

}