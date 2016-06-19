package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

/**
 * 企业
 * Created by yanqu on 2016/6/15.
 */
public class Company extends BaseEntity {

    // 审核状态（0：未审核，1：审核不通过，2：审核通过）
    enum Status implements BaseEnum {
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
    private long userId;
    /**
     * 企业名称
     */
    private String name = Constants.EMPTY;
    /**
     * 企业地址
     */
    private String address = Constants.EMPTY;
    /**
     * 法人
     */
    private String legalPerson = Constants.EMPTY;
    /**
     * 法人身份证号
     */
    private String legalPersonIdNumber = Constants.EMPTY;
    /**
     * 营业执照号码
     */
    private String lisence = Constants.EMPTY;
    /**
     * 组织机构代码
     */
    private String orgCode = Constants.EMPTY;
    /**
     * 税务登记号
     */
    private String taxCode = Constants.EMPTY;
    /**
     * 法人身份证正面照
     */
    private String legalPersonIdPositive = Constants.BLANK_SPACE;
    /**
     * 法人身份证反面照
     */
    private String legalPersonIdNegtive = Constants.EMPTY;
    /**
     * 营业执照原件照片
     */
    private String lisencePic = Constants.EMPTY;
    /**
     * 税务登记号原件照片
     */
    private String taxPic = Constants.EMPTY;
    /**
     * 组织机构代码原件照片
     */
    private String orgPic = Constants.EMPTY;
    /**
     * 是否认证
     */
    private boolean authed;
    /**
     * 审核状态
     */
    private Status status;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系人手机号
     */
    private String mobile;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getLisence() {
        return lisence;
    }

    public void setLisence(String lisence) {
        this.lisence = lisence;
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

    public String getLisencePic() {
        return lisencePic;
    }

    public void setLisencePic(String lisencePic) {
        this.lisencePic = lisencePic;
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
