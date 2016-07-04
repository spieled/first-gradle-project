package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现记录
 * Created by yanqu on 2016/6/16.
 */
@Entity
public class WithdrawRecord extends BaseEntity {
    private static final long serialVersionUID = 2750229415661498539L;

    // 审核状态（0：未审核，1：审核不通过，2：审核通过）
    enum Status implements BaseEnum {
        NEW("未审核"),
        CHECK_FAILED("审核不通过"),
        CHECK_SUCCESS("审核通过"),
        BANK_PROCESS("银行处理中"),
        COMPLETED("完成")
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
     * 账户ID
     */
    @Column(name = "account_id")
    private long accountId;
    /**
     * 提现金额
     */
    @Column(name = "amount")
    private BigDecimal amount;
    /**
     * 银行卡所属银行
     */
    @Column(name = "bank")
    private String bank;
    /**
     * 银行卡号
     */
    @Column(name = "card_number")
    private String cardNumber;
    /**
     * 开户行
     */
    @Column(name = "open_bank")
    private String openBank;
    /**
     * 提现状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * 备注
     */
    @Column(name = "note")
    private String note;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
