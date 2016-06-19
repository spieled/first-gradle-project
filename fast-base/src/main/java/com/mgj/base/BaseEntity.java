package com.mgj.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/14.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5096361521169232725L;
    private long id;
    private Date createTime = new Date();

    public long getId() {
        return id;
    }

    /**
     * 唯一标识
     * @param id 唯一标识identify
     */
    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
