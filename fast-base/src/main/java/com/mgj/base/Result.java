package com.mgj.base;

/**
 * REST接口的返回结果对象
 * Created by yanqu on 2016/6/26.
 */
public class Result {
    private boolean success;
    private String msg;
    private Object content;

    public static final Result ok() {
        return new Result(true, "成功", "");
    }

    public static final Result ok(String msg, Object content) {
        return new Result(true, msg, content);
    }

    public static final Result fail() {
        return new Result(false, "失败", "");
    }

    public static final Result fail(String msg, Object content) {
        return new Result(false, msg, content);
    }

    public Result() {
    }

    public Result(boolean success, String msg, Object content) {
        this.success = success;
        this.msg = msg;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
