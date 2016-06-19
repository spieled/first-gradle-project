package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;

/**
 * 站内消息
 * Created by yanqu on 2016/6/16.
 */
public class Message extends BaseEntity {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 消息类型
     */
    private int type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否已读
     */
    private boolean readed;
    /**
     * 是否已删除
     */
    private boolean deleted;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
