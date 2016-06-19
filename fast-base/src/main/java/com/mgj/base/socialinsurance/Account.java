package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;

import java.math.BigDecimal;

/**
 * 账户
 * Created by yanqu on 2016/6/15.
 */
public class Account extends BaseEntity {
    enum Type implements BaseEnum {
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
    private long userId;
    /**
     * 账户类型
     */
    private Type type = Type.PERSONEL;
    /**
     * 账户余额
     */
    private BigDecimal balance = BigDecimal.ZERO;
    /**
     * 冻结金额
     */
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
}
