package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;

import java.math.BigDecimal;

/**
 * 订单
 * Created by yanqu on 2016/6/16.
 */
public class Order extends BaseEntity {
    // 订单状态（0：待支付，1：已取消，2：已支付）
    enum Status implements BaseEnum {
        WAIT_PAY("待支付"),
        CANCELED("已取消"),
        PAYED("已支付")
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
    private String userId;
    /**
     * 订单号
     */
    private String serialNumber;
    /**
     * 订单标题
     */
    private String title;
    /**
     * 订单明细条数
     */
    private int itemNumber;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 订单状态
     */
    private Status status;
    /**
     * 是否已删除
     */
    private boolean deleted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
