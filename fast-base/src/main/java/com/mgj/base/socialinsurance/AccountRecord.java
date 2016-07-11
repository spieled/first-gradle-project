package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * 账户变更记录
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class AccountRecord extends BaseEntity {
    private static final long serialVersionUID = -2350879273881339807L;

    // 变更类型（1：微信支付，2：支付宝支付，3：银联支付，4：线下汇款）
    public enum Type implements BaseEnum {
        UNKNOWN("保密"),
        WEIXIN("微信支付"),
        ALIPAY("支付宝支付"),
        UNIONPAY("银联支付"),
        OFFLINE("线下汇款")
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
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 账户ID
     */
    @Column(name = "account_id")
    private long accountId;
    /**
     * 变更金额
     */
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;
    /**
     * 支付渠道类型
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type = Type.UNKNOWN;
    /**
     * 备注
     */
    @Column(name = "note")
    private String note = Constants.EMPTY;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
