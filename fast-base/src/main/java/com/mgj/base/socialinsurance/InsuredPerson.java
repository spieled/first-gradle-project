package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

import java.util.Date;

/**
 * 参保人
 * Created by yanqu on 2016/6/15.
 */
public class InsuredPerson extends BaseEntity {
    enum Status implements BaseEnum {
        NEW("未审核"),
        CHECK_FAILED("审核不通过"),
        CHECK_SUCCESS("审核通过")
        ;

        private String display;
        Status(String display) {
            this.display = display;
        }
        @Override
        public String display() {
            return this.display;
        }
    }

    enum Type implements BaseEnum {
        CITY("城镇户籍"),
        TOWN("农村户籍")
        ;

        private String display;
        Type(String display) {
            this.display = display;
        }
        @Override
        public String display() {
            return this.display;
        }
    }

    /**
     * 用户ID
     */
    private long userId;
    /**
     *  公司ID
     */
    private long companyId;
    /**
     * 参保人姓名
     */
    private String name = Constants.EMPTY;
    /**
     * 身份证号
     */
    private String idNumber = Constants.EMPTY;
    /**
     * 户籍所在地城市ID
     */
    private int city;
    /**
     * 户籍类型
     */
    private Type type;
    /**
     * 审核状态
     */
    private Status status = Status.NEW;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String note = Constants.EMPTY;
    /**
     * 是否在职,true：在职；false：离职
     */
    private boolean onStation;
    /**
     * 是否曾经参保，true：曾经参保；false：从未参保
     */
    private boolean insured;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isOnStation() {
        return onStation;
    }

    public void setOnStation(boolean onStation) {
        this.onStation = onStation;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }
}
