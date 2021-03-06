package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * 账户
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class Account extends BaseEntity {
    private static final long serialVersionUID = 7986892604187641226L;

    public enum Type implements BaseEnum {
        PERSONEL("个人"),
        COMPANY("企业")
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
     * 账户名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 企业ID
     */
    @Column(name = "company_id")
    private long companyId;
    /**
     * 账户类型
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type = Type.PERSONEL;
    /**
     * 账户余额
     */
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;
    /**
     * 冻结金额
     */
    @Column(name = "frozen_amount")
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
