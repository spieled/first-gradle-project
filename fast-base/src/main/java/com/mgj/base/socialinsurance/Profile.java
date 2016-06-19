package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.Gender;

import java.util.Date;

/**
 * 个人资料
 * Created by yanqu on 2016/6/15.
 */
public class Profile extends BaseEntity {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 用户头像URL地址
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private Gender gender = Gender.UNKNOWN;
    /**
     * 生日
     */
    private Date birth;
    /**
     * 行业
     */
    private String profession;
    /**
     * 身份证正面照URL地址
     */
    private String idPositive;
    /**
     * 身份证反面照URL地址
     */
    private String idNegtive;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 是否实名认证，true：实名认证；false：未实名认证；
     */
    private boolean realAuthed;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIdPositive() {
        return idPositive;
    }

    public void setIdPositive(String idPositive) {
        this.idPositive = idPositive;
    }

    public String getIdNegtive() {
        return idNegtive;
    }

    public void setIdNegtive(String idNegtive) {
        this.idNegtive = idNegtive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isRealAuthed() {
        return realAuthed;
    }

    public void setRealAuthed(boolean realAuthed) {
        this.realAuthed = realAuthed;
    }
}
