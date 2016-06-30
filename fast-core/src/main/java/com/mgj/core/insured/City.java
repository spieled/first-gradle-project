package com.mgj.core.insured;

import com.mgj.base.BaseEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/30.
 */
public class City implements Serializable {

    static enum Level implements BaseEnum {
        PROVINCE("省"),CITY("市") ;
        private String display;
        Level(String display) {
            this.display = display;
        }
        @Override
        public String display() {
            return this.display;
        }


    }
    private long id;
    private String name;
    private Level level;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
