package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * 个人资料
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class Profile extends BaseEntity {
    private static final long serialVersionUID = -7927107618582267869L;
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private long userId;
    /**
     * 用户头像URL地址
     */
    @Column(name = "avatar")
    private String avatar;
    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 性别
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;
    /**
     * 生日
     */
    @Column(name = "birth")
    private Date birth;
    /**
     * 行业
     */
    @Column(name = "profession")
    private String profession;
    /**
     * 身份证正面照URL地址
     */
    @Column(name = "id_positive")
    private String idPositive;
    /**
     * 身份证反面照URL地址
     */
    @Column(name = "id_negtive")
    private String idNegtive;
    /**
     * 邮箱地址
     */
    @Column(name = "email")
    private String email;
    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 是否实名认证，true：实名认证；false：未实名认证；
     */
    @Column(name = "real_authed")
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
