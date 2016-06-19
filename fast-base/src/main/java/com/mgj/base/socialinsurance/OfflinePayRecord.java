package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;
import com.mgj.base.Constants;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 线下汇款记录
 * Created by yanqu on 2016/6/15.
 */
public class OfflinePayRecord extends BaseEntity {
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
     * 汇款金额
     */
    private BigDecimal amount = BigDecimal.ZERO;
    /**
     * 汇款银行
     */
    private String bank = Constants.EMPTY;
    /**
     * 汇款凭证小票URL地址
     */
    private String ticket = Constants.EMPTY;
    /**
     * 审核状态
     */
    private Status status = Status.NEW;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 银行流水号
     */
    private String serialNumber = Constants.EMPTY;
    /**
     * 银行卡号
     */
    private String cardNumber = Constants.EMPTY;
    /**
     * 账户ID
     */
    private long accountId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
