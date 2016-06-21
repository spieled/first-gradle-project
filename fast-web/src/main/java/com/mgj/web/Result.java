package com.mgj.web;

/**
 * Created by yanqu on 2016/6/21.
 */
public class Result {
    private int status;
    private String msg;
    private int count;
    public Result() {}
    public Result(int status, String msg, int count) {
        this.status = status;
        this.msg = msg;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
