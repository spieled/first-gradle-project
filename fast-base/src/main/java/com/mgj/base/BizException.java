package com.mgj.base;

/**
 * 自定义业务异常
 * Created by yanqu on 2016/7/18.
 */
public class BizException extends Exception {
    public BizException(String message) {
        super(message);
    }
}
