package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单明细
 * Created by yanqu on 2016/6/16.
 */
public class OrderItem extends BaseEntity {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 订单ID
     */
    private long orderId;
    /**
     * 参保人ID
     */
    private long insurePersonId;
    /**
     * 参保城市
     */
    private int insureCity;
    /**
     * 缴费基数
     */
    private BigDecimal insureBase = BigDecimal.ZERO;
    /**
     * 养老保险公司比例
     */
    private BigDecimal endowmentPercentCompany = BigDecimal.ZERO;
    /**
     * 养老保险公司金额
     */
    private BigDecimal endowmentCompany = BigDecimal.ZERO;
    /**
     * 养老保险个人比例
     */
    private BigDecimal endowmentPercentPerson = BigDecimal.ZERO;
    /**
     * 养老保险个人金额
     */
    private BigDecimal endowmentPerson = BigDecimal.ZERO;

    /**
     * 基本医疗保险公司比例
     */
    private BigDecimal medicalPercentCompany = BigDecimal.ZERO;
    /**
     * 基本医疗保险公司金额
     */
    private BigDecimal medicalCompany = BigDecimal.ZERO;
    /**
     * 基本医疗保险个人比例
     */
    private BigDecimal medicalPercentPerson = BigDecimal.ZERO;
    /**
     * 基本医疗保险个人金额
     */
    private BigDecimal medicalPerson = BigDecimal.ZERO;

    /**
     * 失业保险公司比例
     */
    private BigDecimal unemployedPercentCompany = BigDecimal.ZERO;
    /**
     * 失业保险公司金额
     */
    private BigDecimal unemployedCompany = BigDecimal.ZERO;
    /**
     * 失业保险个人比例
     */
    private BigDecimal unemployedPercentPerson = BigDecimal.ZERO;
    /**
     * 失业保险个人金额
     */
    private BigDecimal unemployedPerson = BigDecimal.ZERO;

    /**
     * 生育保险公司比例
     */
    private BigDecimal maternityPercentCompany = BigDecimal.ZERO;
    /**
     * 生育保险公司金额
     */
    private BigDecimal maternityCompany = BigDecimal.ZERO;
    /**
     * 生育保险个人比例
     */
    private BigDecimal maternityPercentPerson = BigDecimal.ZERO;
    /**
     * 生育保险个人金额
     */
    private BigDecimal maternityPerson = BigDecimal.ZERO;

    /**
     * 工伤保险公司比例
     */
    private BigDecimal injuryPercentCompany = BigDecimal.ZERO;
    /**
     * 工伤保险公司金额
     */
    private BigDecimal injuryCompany = BigDecimal.ZERO;
    /**
     * 工伤保险个人比例
     */
    private BigDecimal injuryPercentPerson = BigDecimal.ZERO;
    /**
     * 工伤保险个人金额
     */
    private BigDecimal injuryPerson = BigDecimal.ZERO;

    /**
     * 大病保险公司比例
     */
    private BigDecimal sickPercentCompany = BigDecimal.ZERO;
    /**
     * 大病保险公司金额
     */
    private BigDecimal sickCompany = BigDecimal.ZERO;
    /**
     * 大病保险个人比例
     */
    private BigDecimal sickPercentPerson = BigDecimal.ZERO;
    /**
     * 大病保险个人金额
     */
    private BigDecimal sickPerson = BigDecimal.ZERO;

    /**
     * 公司总比例
     */
    private BigDecimal totalPercentCompany = BigDecimal.ZERO;
    /**
     * 公司总金额
     */
    private BigDecimal totalCompany = BigDecimal.ZERO;
    /**
     * 个人总比例
     */
    private BigDecimal totalPercentPerson = BigDecimal.ZERO;
    /**
     * 个人总金额
     */
    private BigDecimal totalPerson = BigDecimal.ZERO;

    /**
     * 缴社保总金额
     */
    private BigDecimal total = BigDecimal.ZERO;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getInsurePersonId() {
        return insurePersonId;
    }

    public void setInsurePersonId(long insurePersonId) {
        this.insurePersonId = insurePersonId;
    }

    public int getInsureCity() {
        return insureCity;
    }

    public void setInsureCity(int insureCity) {
        this.insureCity = insureCity;
    }

    public BigDecimal getInsureBase() {
        return insureBase;
    }

    public void setInsureBase(BigDecimal insureBase) {
        this.insureBase = insureBase;
    }

    public BigDecimal getEndowmentPercentCompany() {
        return endowmentPercentCompany;
    }

    public void setEndowmentPercentCompany(BigDecimal endowmentPercentCompany) {
        this.endowmentPercentCompany = endowmentPercentCompany;
    }

    public BigDecimal getEndowmentCompany() {
        return endowmentCompany;
    }

    public void setEndowmentCompany(BigDecimal endowmentCompany) {
        this.endowmentCompany = endowmentCompany;
    }

    public BigDecimal getEndowmentPercentPerson() {
        return endowmentPercentPerson;
    }

    public void setEndowmentPercentPerson(BigDecimal endowmentPercentPerson) {
        this.endowmentPercentPerson = endowmentPercentPerson;
    }

    public BigDecimal getEndowmentPerson() {
        return endowmentPerson;
    }

    public void setEndowmentPerson(BigDecimal endowmentPerson) {
        this.endowmentPerson = endowmentPerson;
    }

    public BigDecimal getMedicalPercentCompany() {
        return medicalPercentCompany;
    }

    public void setMedicalPercentCompany(BigDecimal medicalPercentCompany) {
        this.medicalPercentCompany = medicalPercentCompany;
    }

    public BigDecimal getMedicalCompany() {
        return medicalCompany;
    }

    public void setMedicalCompany(BigDecimal medicalCompany) {
        this.medicalCompany = medicalCompany;
    }

    public BigDecimal getMedicalPercentPerson() {
        return medicalPercentPerson;
    }

    public void setMedicalPercentPerson(BigDecimal medicalPercentPerson) {
        this.medicalPercentPerson = medicalPercentPerson;
    }

    public BigDecimal getMedicalPerson() {
        return medicalPerson;
    }

    public void setMedicalPerson(BigDecimal medicalPerson) {
        this.medicalPerson = medicalPerson;
    }

    public BigDecimal getUnemployedPercentCompany() {
        return unemployedPercentCompany;
    }

    public void setUnemployedPercentCompany(BigDecimal unemployedPercentCompany) {
        this.unemployedPercentCompany = unemployedPercentCompany;
    }

    public BigDecimal getUnemployedCompany() {
        return unemployedCompany;
    }

    public void setUnemployedCompany(BigDecimal unemployedCompany) {
        this.unemployedCompany = unemployedCompany;
    }

    public BigDecimal getUnemployedPercentPerson() {
        return unemployedPercentPerson;
    }

    public void setUnemployedPercentPerson(BigDecimal unemployedPercentPerson) {
        this.unemployedPercentPerson = unemployedPercentPerson;
    }

    public BigDecimal getUnemployedPerson() {
        return unemployedPerson;
    }

    public void setUnemployedPerson(BigDecimal unemployedPerson) {
        this.unemployedPerson = unemployedPerson;
    }

    public BigDecimal getMaternityPercentCompany() {
        return maternityPercentCompany;
    }

    public void setMaternityPercentCompany(BigDecimal maternityPercentCompany) {
        this.maternityPercentCompany = maternityPercentCompany;
    }

    public BigDecimal getMaternityCompany() {
        return maternityCompany;
    }

    public void setMaternityCompany(BigDecimal maternityCompany) {
        this.maternityCompany = maternityCompany;
    }

    public BigDecimal getMaternityPercentPerson() {
        return maternityPercentPerson;
    }

    public void setMaternityPercentPerson(BigDecimal maternityPercentPerson) {
        this.maternityPercentPerson = maternityPercentPerson;
    }

    public BigDecimal getMaternityPerson() {
        return maternityPerson;
    }

    public void setMaternityPerson(BigDecimal maternityPerson) {
        this.maternityPerson = maternityPerson;
    }

    public BigDecimal getInjuryPercentCompany() {
        return injuryPercentCompany;
    }

    public void setInjuryPercentCompany(BigDecimal injuryPercentCompany) {
        this.injuryPercentCompany = injuryPercentCompany;
    }

    public BigDecimal getInjuryCompany() {
        return injuryCompany;
    }

    public void setInjuryCompany(BigDecimal injuryCompany) {
        this.injuryCompany = injuryCompany;
    }

    public BigDecimal getInjuryPercentPerson() {
        return injuryPercentPerson;
    }

    public void setInjuryPercentPerson(BigDecimal injuryPercentPerson) {
        this.injuryPercentPerson = injuryPercentPerson;
    }

    public BigDecimal getInjuryPerson() {
        return injuryPerson;
    }

    public void setInjuryPerson(BigDecimal injuryPerson) {
        this.injuryPerson = injuryPerson;
    }

    public BigDecimal getSickPercentCompany() {
        return sickPercentCompany;
    }

    public void setSickPercentCompany(BigDecimal sickPercentCompany) {
        this.sickPercentCompany = sickPercentCompany;
    }

    public BigDecimal getSickCompany() {
        return sickCompany;
    }

    public void setSickCompany(BigDecimal sickCompany) {
        this.sickCompany = sickCompany;
    }

    public BigDecimal getSickPercentPerson() {
        return sickPercentPerson;
    }

    public void setSickPercentPerson(BigDecimal sickPercentPerson) {
        this.sickPercentPerson = sickPercentPerson;
    }

    public BigDecimal getSickPerson() {
        return sickPerson;
    }

    public void setSickPerson(BigDecimal sickPerson) {
        this.sickPerson = sickPerson;
    }

    public BigDecimal getTotalPercentCompany() {
        return totalPercentCompany;
    }

    public void setTotalPercentCompany(BigDecimal totalPercentCompany) {
        this.totalPercentCompany = totalPercentCompany;
    }

    public BigDecimal getTotalCompany() {
        return totalCompany;
    }

    public void setTotalCompany(BigDecimal totalCompany) {
        this.totalCompany = totalCompany;
    }

    public BigDecimal getTotalPercentPerson() {
        return totalPercentPerson;
    }

    public void setTotalPercentPerson(BigDecimal totalPercentPerson) {
        this.totalPercentPerson = totalPercentPerson;
    }

    public BigDecimal getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(BigDecimal totalPerson) {
        this.totalPerson = totalPerson;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
