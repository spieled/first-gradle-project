package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;
import com.mgj.base.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;
import java.util.Date;

/**
 * 参保人
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class InsuredPerson extends BaseEntity {
    private static final long serialVersionUID = 3128940406108785070L;

    public enum Status implements BaseEnum {
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

    public enum Type implements BaseEnum {
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
    @Column(name = "user_id")
    private long userId;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     *  公司ID
     */
    @Column(name = "company_id")
    private long companyId;
    /**
     * 参保人姓名
     */
    @Column(name = "name")
    private String name = Constants.EMPTY;
    /**
     * 身份证号
     */
    @Column(name = "id_number")
    private String idNumber = Constants.EMPTY;
    /**
     * 户籍所在地城市ID
     */
    @Column(name = "city")
    private int city;
    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;
    /**
     * 户籍类型
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    /**
     * 出生年月日
     */
    @Column(name = "birth_ymd")
    private int birthYmd;
    /**
     * 性别
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /**
     * 审核状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 备注
     */
    @Column(name = "note")
    private String note = Constants.EMPTY;
    /**
     * 是否在职,true：在职；false：离职
     */
    @Column(name = "on_station")
    private boolean onStation;
    /**
     * 是否曾经参保，true：曾经参保；false：从未参保
     */
    @Column(name = "insured")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getBirthYmd() {
        return birthYmd;
    }

    public void setBirthYmd(int birthYmd) {
        this.birthYmd = birthYmd;
    }
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR)*100 + calendar.get(Calendar.MONTH) + 1;
        int birthYear = this.birthYmd/100;
        return (currentYear - birthYear)/100 +1;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
