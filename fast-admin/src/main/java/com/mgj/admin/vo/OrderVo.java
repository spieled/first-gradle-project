package com.mgj.admin.vo;

import java.math.BigDecimal;

/**
 * Created by yanqu on 2016/7/13.
 */
public class OrderVo {
    // 参保人ID
    private long personId;
    // 缴费基数
    private BigDecimal insureBase;
    // 是否投保大病医疗
    private boolean sick;
    // 类型
    private String type;
    // 投保城市，默认成都
    private int city = 510100;
    /**
     * 缴费月份
     */
    private int insureYm;

    public int getInsureYm() {
        return insureYm;
    }

    public void setInsureYm(int insureYm) {
        this.insureYm = insureYm;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public BigDecimal getInsureBase() {
        return insureBase;
    }

    public void setInsureBase(BigDecimal insureBase) {
        this.insureBase = insureBase;
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
