package com.mgj.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/14.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1011494802786080954L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "create_time")
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
