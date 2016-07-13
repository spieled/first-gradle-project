package com.mgj.util.upyun;

import com.mgj.base.Constants;
import main.java.com.UpYun;

import java.io.File;
import java.io.IOException;

/**
 * Created by yanqu on 2016/7/13.
 */
public class UpyunPicBucket implements PicBucket {
    /** upyun instance */
    private UpYun upYun;
    /** 绑定的域名 */
    private String URL;

    /**
     * @param bucketName 服务名称
     * @param username 操作员名
     * @param userPwd 操作员密码
     * @return upyun
     */
    public UpyunPicBucket(String bucketName, String username, String userPwd) {
        this(bucketName, username, userPwd, false, 60);
    }

    /**
     * @param bucketName 服务名称
     * @param username 操作员名
     * @param userPwd 操作员密码
     * @param debug 调试模式
     * @param timeout 超市时间，单位秒
     * @return upyun
     */
    public UpyunPicBucket(String bucketName, String username, String userPwd, boolean debug, int timeout) {
        upYun = new UpYun(bucketName, username, userPwd);
        upYun.setDebug(true);
        upYun.setTimeout(60); // unit second
        URL = "http://" + bucketName + ".b0.upaiyun.com";
    }

    private String fullUrl(String filePath) {
        if (filePath == null) {
            return Constants.EMPTY;
        }
        if (filePath.startsWith(DIR_ROOT)) {
            return URL + filePath;
        } else {
            return URL + DIR_ROOT + filePath;
        }
    }

    /**
     * 上传文件
     * @param filePath
     * @param datas
     * @param auto
     * @return
     */
    public String writeFile(String filePath, String datas, boolean auto) {
        if(upYun.writeFile(filePath, datas, auto)) {
            return fullUrl(filePath);
        }
        return Constants.EMPTY;
    }

    /**
     * 上传文件
     * @param filePath
     * @param file
     * @param auto
     * @return
     * @throws IOException
     */
    public String writeFile(String filePath, File file, boolean auto) throws IOException {
        if (upYun.writeFile(filePath, file, auto)) {
            return fullUrl(filePath);
        }
        return Constants.EMPTY;
    }

    /**
     * 上传文件
     * @param filePath
     * @param datas
     * @param auto
     * @return
     */
    public String writeFile(String filePath, byte[] datas, boolean auto) {
        if (upYun.writeFile(filePath, datas, auto)) {
           return fullUrl(filePath);
        }
        return Constants.EMPTY;
    }
}
