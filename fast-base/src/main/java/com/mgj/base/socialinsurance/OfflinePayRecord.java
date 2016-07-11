package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 线下汇款记录
 * Created by yanqu on 2016/6/15.
 */
@Entity
public class OfflinePayRecord extends BaseEntity {
    private static final long serialVersionUID = -8224135770834484150L;

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
     * 汇款金额
     */
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;
    /**
     * 汇款银行
     */
    @Column(name = "bank")
    private String bank = Constants.EMPTY;
    /**
     * 汇款凭证小票URL地址
     */
    @Column(name = "ticket")
    private String ticket = Constants.EMPTY;
    /**
     * 审核状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 银行流水号
     */
    @Column(name = "serial_number")
    private String serialNumber = Constants.EMPTY;
    /**
     * 银行卡号
     */
    @Column(name = "card_number")
    private String cardNumber = Constants.EMPTY;
    /**
     * 账户ID
     */
    @Column(name = "account_id")
    private long accountId;

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
