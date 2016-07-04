package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 站内消息
 * Created by yanqu on 2016/6/16.
 */
@Entity
public class Message extends BaseEntity {
    private static final long serialVersionUID = 5744115947980257615L;
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private long userId;
    /**
     * 消息类型
     */
    @Column(name = "type")
    private int type;
    /**
     * 消息内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 是否已读
     */
    @Column(name = "readed")
    private boolean readed;
    /**
     * 是否已删除
     */
    @Column(name = "deleted")
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
