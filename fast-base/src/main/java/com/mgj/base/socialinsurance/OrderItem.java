package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * 订单明细
 * Created by yanqu on 2016/6/16.
 */
@Entity
public class OrderItem extends BaseEntity {
    private static final long serialVersionUID = 2905792434092113354L;
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
     * 订单ID
     */
    @Column(name = "order_id")
    private long orderId;
    /**
     * 参保人ID
     */
    @Column(name = "insure_person_id")
    private long insurePersonId;
    /**
     * 参保城市
     */
    @Column(name = "insure_city")
    private int insureCity;
    /**
     * 缴费基数
     */
    @Column(name = "insure_base")
    private BigDecimal insureBase = BigDecimal.ZERO;
    /**
     * 养老保险公司比例
     */
    @Column(name = "endowment_percent_company")
    private BigDecimal endowmentPercentCompany = BigDecimal.ZERO;
    /**
     * 养老保险公司金额
     */
    @Column(name = "endowment_company")
    private BigDecimal endowmentCompany = BigDecimal.ZERO;
    /**
     * 养老保险个人比例
     */
    @Column(name = "endowment_percent_person")
    private BigDecimal endowmentPercentPerson = BigDecimal.ZERO;
    /**
     * 养老保险个人金额
     */
    @Column(name = "endowment_person")
    private BigDecimal endowmentPerson = BigDecimal.ZERO;

    /**
     * 基本医疗保险公司比例
     */
    @Column(name = "medical_percent_company")
    private BigDecimal medicalPercentCompany = BigDecimal.ZERO;
    /**
     * 基本医疗保险公司金额
     */
    @Column(name = "medical_company")
    private BigDecimal medicalCompany = BigDecimal.ZERO;
    /**
     * 基本医疗保险个人比例
     */
    @Column(name = "medical_percent_person")
    private BigDecimal medicalPercentPerson = BigDecimal.ZERO;
    /**
     * 基本医疗保险个人金额
     */
    @Column(name = "medical_person")
    private BigDecimal medicalPerson = BigDecimal.ZERO;

    /**
     * 失业保险公司比例
     */
    @Column(name = "unemployed_percent_company")
    private BigDecimal unemployedPercentCompany = BigDecimal.ZERO;
    /**
     * 失业保险公司金额
     */
    @Column(name = "unemployed_company")
    private BigDecimal unemployedCompany = BigDecimal.ZERO;
    /**
     * 失业保险个人比例
     */
    @Column(name = "unemployed_percent_person")
    private BigDecimal unemployedPercentPerson = BigDecimal.ZERO;
    /**
     * 失业保险个人金额
     */
    @Column(name = "unemployed_person")
    private BigDecimal unemployedPerson = BigDecimal.ZERO;

    /**
     * 生育保险公司比例
     */
    @Column(name = "maternity_percent_company")
    private BigDecimal maternityPercentCompany = BigDecimal.ZERO;
    /**
     * 生育保险公司金额
     */
    @Column(name = "maternity_company")
    private BigDecimal maternityCompany = BigDecimal.ZERO;
    /**
     * 生育保险个人比例
     */
    @Column(name = "maternity_percent_person")
    private BigDecimal maternityPercentPerson = BigDecimal.ZERO;
    /**
     * 生育保险个人金额
     */
    @Column(name = "maternity_person")
    private BigDecimal maternityPerson = BigDecimal.ZERO;

    /**
     * 工伤保险公司比例
     */
    @Column(name = "injury_percent_company")
    private BigDecimal injuryPercentCompany = BigDecimal.ZERO;
    /**
     * 工伤保险公司金额
     */
    @Column(name = "injury_company")
    private BigDecimal injuryCompany = BigDecimal.ZERO;
    /**
     * 工伤保险个人比例
     */
    @Column(name = "injury_percent_person")
    private BigDecimal injuryPercentPerson = BigDecimal.ZERO;
    /**
     * 工伤保险个人金额
     */
    @Column(name = "injury_person")
    private BigDecimal injuryPerson = BigDecimal.ZERO;

    /**
     * 大病保险公司比例
     */
    @Column(name = "sick_percent_company")
    private BigDecimal sickPercentCompany = BigDecimal.ZERO;
    /**
     * 大病保险公司金额
     */
    @Column(name = "sick_company")
    private BigDecimal sickCompany = BigDecimal.ZERO;
    /**
     * 大病保险个人比例
     */
    @Column(name = "sick_percent_person")
    private BigDecimal sickPercentPerson = BigDecimal.ZERO;
    /**
     * 大病保险个人金额
     */
    @Column(name = "sick_person")
    private BigDecimal sickPerson = BigDecimal.ZERO;

    /**
     * 公司总比例
     */
    @Column(name = "total_percent_company")
    private BigDecimal totalPercentCompany = BigDecimal.ZERO;
    /**
     * 公司总金额
     */
    @Column(name = "total_company")
    private BigDecimal totalCompany = BigDecimal.ZERO;
    /**
     * 个人总比例
     */
    @Column(name = "total_percent_person")
    private BigDecimal totalPercentPerson = BigDecimal.ZERO;
    /**
     * 个人总金额
     */
    @Column(name = "total_person")
    private BigDecimal totalPerson = BigDecimal.ZERO;

    /**
     * 缴社保总金额
     */
    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;
    /**
     * 总比例
     */
    @Column(name = "total_percent")
    private BigDecimal totalPercent = BigDecimal.ZERO;

    public BigDecimal getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(BigDecimal totalPercent) {
        this.totalPercent = totalPercent;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static OrderItem calc(BigDecimal insureBase, InsurePolicy policy) {
        OrderItem instance = new OrderItem();
        instance.endowmentPercentCompany = policy.getEndowmentPercentCompany();
        instance.endowmentPercentPerson = policy.getEndowmentPercentPerson();
        instance.medicalPercentCompany = policy.getMedicalPercentCompany();
        instance.medicalPercentPerson = policy.getMedicalPercentPerson();
        instance.unemployedPercentCompany = policy.getUnemployedPercentCompany();
        instance.unemployedPercentPerson = policy.getUnemployedPercentPerson();
        instance.maternityPercentCompany = policy.getMaternityPercentCompany();
        instance.maternityPercentPerson = policy.getMaternityPercentPerson();
        instance.injuryPercentCompany = policy.getInjuryPercentCompany();
        instance.injuryPercentPerson = policy.getInjuryPercentPerson();
        instance.sickPercentCompany = policy.getSickPercentCompany();
        instance.sickPercentPerson = policy.getSickPercentPerson();
        instance.totalPercentCompany = policy.getTotalPercentCompany();
        instance.totalPercentPerson = policy.getTotalPercentPerson();
        instance.totalPercent = policy.getTotalPercent();

        instance.endowmentCompany = insureBase.multiply(instance.getEndowmentPercentCompany());
        instance.endowmentPerson = insureBase.multiply(instance.getEndowmentPercentPerson());
        instance.medicalCompany = insureBase.multiply(instance.getMedicalPercentCompany());
        instance.medicalPerson = insureBase.multiply(instance.getMedicalPercentPerson());
        instance.unemployedCompany = insureBase.multiply(instance.getUnemployedPercentCompany());
        instance.unemployedPerson = insureBase.multiply(instance.getUnemployedPercentPerson());
        instance.maternityCompany = insureBase.multiply(instance.getMaternityPercentCompany());
        instance.maternityPerson = insureBase.multiply(instance.getMaternityPercentPerson());
        instance.injuryCompany = insureBase.multiply(instance.getInjuryPercentCompany());
        instance.injuryPerson = insureBase.multiply(instance.getInjuryPercentPerson());
        instance.sickCompany = insureBase.multiply(instance.getSickPercentCompany());
        instance.sickPerson = insureBase.multiply(instance.getSickPercentPerson());
        instance.totalCompany = insureBase.multiply(instance.getTotalPercentCompany());
        instance.totalPerson = insureBase.multiply(instance.getTotalPercentPerson());
        instance.total = instance.totalCompany.add(instance.totalPerson);

        return instance;

    }
}
