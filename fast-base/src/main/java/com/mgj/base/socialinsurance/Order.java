package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;
import com.mgj.base.BaseEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * 订单
 * Created by yanqu on 2016/6/16.
 */
@Entity(name = "insure_order")
public class Order extends BaseEntity {
    private static final long serialVersionUID = -5497951034778555855L;

    // 订单状态（0：待支付，1：已取消，2：已支付）
    public enum Status implements BaseEnum {
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
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 订单号
     */
    @Column(name = "serial_number")
    private String serialNumber;
    /**
     * 订单标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 订单明细条数
     */
    @Column(name = "item_number")
    private int itemNumber;
    /**
     * 总价
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    /**
     * 订单状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT_PAY;
    /**
     * 是否已删除
     */
    @Column(name = "deleted")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
