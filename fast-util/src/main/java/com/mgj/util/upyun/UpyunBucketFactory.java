package com.mgj.util.upyun;

import main.java.com.UpYun;

import java.util.List;

/**
 * 与又拍云通信交流的工具类
 * Created by yanqu on 2016/7/13.
 */
public class UpyunBucketFactory {

    /**
     * @param bucketName 服务名称
     * @param username 操作员名
     * @param userPwd 操作员密码
     * @return upyun
     */
    public static UpyunPicBucket build(String bucketName, String username, String userPwd) {
        return build(bucketName, username, userPwd, false, 60);

    }

    /**
     * @param bucketName 服务名称
     * @param username 操作员名
     * @param userPwd 操作员密码
     * @param debug 调试模式
     * @param timeout 超市时间，单位秒
     * @return upyun
     */
    public static UpyunPicBucket build(String bucketName, String username, String userPwd, boolean debug, int timeout) {
        return new UpyunPicBucket(bucketName, username, userPwd, debug, timeout);
    }

    private static final String BUCKET_NAME = "服务名称";
    private static final String USER_NAME = "操作员名";
    private static final String USER_PWD = "操作员密码";
    private static UpYun upYun = new UpYun(BUCKET_NAME, USER_NAME, USER_PWD);
    static {
        upYun.setDebug(true);
        upYun.setTimeout(60); // unit second
    }

    /**
     * 创建目录
     * @param path
     * @param auto
     */
    public void mkdir(String path, boolean auto) {
        upYun.mkDir(path, auto);
    }

    /**
     * 删除目录
     * @param path
     */
    public void rmDir(String path) {
        upYun.rmDir(path);
    }

    /**
     * 读取目录
     * @param path
     * @return
     */
    public List<UpYun.FolderItem> readDir(String path) {
        return upYun.readDir(path);
    }



}
