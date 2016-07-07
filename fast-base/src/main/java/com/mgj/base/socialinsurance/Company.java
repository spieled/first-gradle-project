package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 企业
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class Company extends BaseEntity {

    private static final long serialVersionUID = -2541583125493336808L;

    // 审核状态（0：未审核，1：审核不通过，2：审核通过）
    public enum Status implements BaseEnum {
        NEW("未审核"),
        CHECK_FAILED("审核不通过"),
        CHECK_SUCCESS("审核通过")
        ;

        private String display;
        Status(String displaly) {
            this.display = displaly;
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
     * 企业名称
     */
    @Column(name = "name")
    private String name = Constants.EMPTY;
    /**
     * 企业地址
     */
    @Column(name = "address")
    private String address = Constants.EMPTY;
    /**
     * 法人
     */
    @Column(name = "legal_person")
    private String legalPerson = Constants.EMPTY;
    /**
     * 法人身份证号
     */
    @Column(name = "legal_person_id_number")
    private String legalPersonIdNumber = Constants.EMPTY;
    /**
     * 营业执照号码
     */
    @Column(name = "license")
    private String license = Constants.EMPTY;
    /**
     * 组织机构代码
     */
    @Column(name = "org_code")
    private String orgCode = Constants.EMPTY;
    /**
     * 税务登记号
     */
    @Column(name = "tax_code")
    private String taxCode = Constants.EMPTY;
    /**
     * 法人身份证正面照
     */
    @Column(name = "legal_person_id_positive")
    private String legalPersonIdPositive = Constants.BLANK_SPACE;
    /**
     * 法人身份证反面照
     */
    @Column(name = "legal_person_id_negtive")
    private String legalPersonIdNegtive = Constants.EMPTY;
    /**
     * 营业执照原件照片
     */
    @Column(name = "license_pic")
    private String licensePic = Constants.EMPTY;
    /**
     * 税务登记号原件照片
     */
    @Column(name = "tax_pic")
    private String taxPic = Constants.EMPTY;
    /**
     * 组织机构代码原件照片
     */
    @Column(name = "org_pic")
    private String orgPic = Constants.EMPTY;
    /**
     * 是否认证
     */
    @Column(name = "authed")
    private boolean authed;
    /**
     * 审核状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;
    /**
     * 联系人
     */
    @Column(name = "contact_person")
    private String contactPerson;
    /**
     * 联系人手机号
     */
    @Column(name = "mobile")
    private String mobile;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonIdNumber() {
        return legalPersonIdNumber;
    }

    public void setLegalPersonIdNumber(String legalPersonIdNumber) {
        this.legalPersonIdNumber = legalPersonIdNumber;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getLegalPersonIdPositive() {
        return legalPersonIdPositive;
    }

    public void setLegalPersonIdPositive(String legalPersonIdPositive) {
        this.legalPersonIdPositive = legalPersonIdPositive;
    }

    public String getLegalPersonIdNegtive() {
        return legalPersonIdNegtive;
    }

    public void setLegalPersonIdNegtive(String legalPersonIdNegtive) {
        this.legalPersonIdNegtive = legalPersonIdNegtive;
    }

    public String getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }

    public String getTaxPic() {
        return taxPic;
    }

    public void setTaxPic(String taxPic) {
        this.taxPic = taxPic;
    }

    public String getOrgPic() {
        return orgPic;
    }

    public void setOrgPic(String orgPic) {
        this.orgPic = orgPic;
    }

    public boolean isAuthed() {
        return authed;
    }

    public void setAuthed(boolean authed) {
        this.authed = authed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
