package com.mgj.base.socialinsurance;

import java.math.BigDecimal;

/**
 * 社保缴费比例政策
 */
public class InsurePolicy {

    private InsurePolicy() {}

    /**
     * 默认获取本地、不缴纳大病保险的投保政策
     * @return
     */
    public static final InsurePolicy getPolicy2016() {
        return getPolicy2016(false, false);
    }

    /**
     * 获取社保缴费比例政策
     * @param sick  是否缴纳大病保险
     * @param otherTown 是否为外地农村户籍
     * @return
     */
    public static final InsurePolicy getPolicy2016(boolean sick, boolean otherTown) {
        InsurePolicy instance = new InsurePolicy();
        instance.endowmentPercentCompany = new BigDecimal("0.2");
        instance.endowmentPercentPerson = new BigDecimal("0.08");
        instance.medicalPercentCompany = new BigDecimal("0.065");
        if (!otherTown) { // 除了异地农村户籍，都要缴纳个人医疗保险
            instance.medicalPercentPerson = new BigDecimal("0.02");
        }
        instance.unemployedPercentCompany = new BigDecimal("0.02");
        instance.unemployedPercentPerson = new BigDecimal("0.01");
        instance.maternityPercentCompany = new BigDecimal("0.006");
        instance.maternityPercentPerson = BigDecimal.ZERO;
        instance.injuryPercentCompany = new BigDecimal("0.006");
        instance.injuryPercentPerson = BigDecimal.ZERO;
        if (sick) { // 大病保险为选择性购买
            instance.sickPercentCompany = new BigDecimal("0.01");
        }
        instance.sickPercentPerson = BigDecimal.ZERO;
        instance.totalPercentCompany = instance.endowmentPercentCompany
                .add(instance.medicalPercentCompany)
                .add(instance.unemployedPercentCompany)
                .add(instance.maternityPercentCompany)
                .add(instance.injuryPercentCompany)
                .add(instance.sickPercentCompany);
        instance.totalPercentPerson = instance.endowmentPercentPerson
                .add(instance.medicalPercentPerson)
                .add(instance.unemployedPercentPerson)
                .add(instance.maternityPercentPerson)
                .add(instance.injuryPercentPerson)
                .add(instance.sickPercentPerson);

        return instance;
    }

    /**
     * 养老保险公司比例
     */
    private BigDecimal endowmentPercentCompany = BigDecimal.ZERO;
    /**
     * 养老保险个人比例
     */
    private BigDecimal endowmentPercentPerson = BigDecimal.ZERO;
    /**
     * 基本医疗保险公司比例
     */
    private BigDecimal medicalPercentCompany = BigDecimal.ZERO;
    /**
     * 基本医疗保险个人比例
     */
    private BigDecimal medicalPercentPerson = BigDecimal.ZERO;
    /**
     * 失业保险公司比例
     */
    private BigDecimal unemployedPercentCompany = BigDecimal.ZERO;
    /**
     * 失业保险个人比例
     */
    private BigDecimal unemployedPercentPerson = BigDecimal.ZERO;
    /**
     * 生育保险公司比例
     */
    private BigDecimal maternityPercentCompany = BigDecimal.ZERO;
    /**
     * 生育保险个人比例
     */
    private BigDecimal maternityPercentPerson = BigDecimal.ZERO;
    /**
     * 工伤保险公司比例
     */
    private BigDecimal injuryPercentCompany = BigDecimal.ZERO;
    /**
     * 工伤保险个人比例
     */
    private BigDecimal injuryPercentPerson = BigDecimal.ZERO;
    /**
     * 大病保险公司比例
     */
    private BigDecimal sickPercentCompany = BigDecimal.ZERO;
    /**
     * 大病保险个人比例
     */
    private BigDecimal sickPercentPerson = BigDecimal.ZERO;
    /**
     * 公司总比例
     */
    private BigDecimal totalPercentCompany = BigDecimal.ZERO;
    /**
     * 个人总比例
     */
    private BigDecimal totalPercentPerson = BigDecimal.ZERO;

    public BigDecimal getEndowmentPercentCompany() {
        return endowmentPercentCompany;
    }

    public BigDecimal getEndowmentPercentPerson() {
        return endowmentPercentPerson;
    }

    public BigDecimal getMedicalPercentCompany() {
        return medicalPercentCompany;
    }

    public BigDecimal getMedicalPercentPerson() {
        return medicalPercentPerson;
    }

    public BigDecimal getUnemployedPercentCompany() {
        return unemployedPercentCompany;
    }

    public BigDecimal getUnemployedPercentPerson() {
        return unemployedPercentPerson;
    }

    public BigDecimal getMaternityPercentCompany() {
        return maternityPercentCompany;
    }

    public BigDecimal getMaternityPercentPerson() {
        return maternityPercentPerson;
    }

    public BigDecimal getInjuryPercentCompany() {
        return injuryPercentCompany;
    }

    public BigDecimal getInjuryPercentPerson() {
        return injuryPercentPerson;
    }

    public BigDecimal getSickPercentCompany() {
        return sickPercentCompany;
    }

    public BigDecimal getSickPercentPerson() {
        return sickPercentPerson;
    }

    public BigDecimal getTotalPercentCompany() {
        return totalPercentCompany;
    }

    public BigDecimal getTotalPercentPerson() {
        return totalPercentPerson;
    }

    public BigDecimal getTotalPercent() {
        return totalPercentCompany.add(totalPercentPerson);
    }
}
