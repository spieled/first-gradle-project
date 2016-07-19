package com.mgj.core.order;

import com.mgj.base.BizException;
import com.mgj.base.socialinsurance.Account;
import com.mgj.base.socialinsurance.AccountRecord;
import com.mgj.base.socialinsurance.Order;
import com.mgj.base.socialinsurance.OrderItem;
import com.mgj.core.base.BaseService;
import com.mgj.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanqu on 2016/7/8.
 */
@Service
public class OrderService extends BaseService<OrderDao, Order, Long> {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao itemDao;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void delete(Long id) {
        super.delete(id);
        itemDao.deleteByOrderId(id);
    }

    @Transactional
    public void createOrder(Order order, List<OrderItem> items) {
        // TODO 验证投保信息
        // TODO 扣除费用
        // TODO 保存投保信息
        Order savedOrder = orderDao.save(order);
        for (OrderItem item : items) {
            item.setOrderId(savedOrder.getId());
            itemDao.save(item);
        }

    }

    public Page<Order> findOrdersByUsername(String username, Pageable pageable) {
        return orderDao.findByUsername(username, pageable);
    }

    @Transactional
    public void payOrder(long orderId, long accountId) throws BizException {
        Order order = orderDao.findOne(orderId);
        if (order == null) {
            throw new BizException("订单不存在");
        }
        if (order.getStatus() == Order.Status.PAYED) {
            throw new BizException("订单已支付，请勿重复支付");
        }
        if (order.getStatus() == Order.Status.CANCELED) {
            throw new BizException("订单已取消，不能支付");
        }
        Account account = userService.findAccountById(accountId);
        if (account.getBalance().compareTo(order.getTotalPrice()) < 0) {
            throw new BizException("账户余额不足");
        }

        // 检查通过之后
        // 1. 减账户余额；2. 添加账户变更记录；3. 更新订单状态；4. 提交快支付或银行处理
        account.setBalance(account.getBalance().subtract(order.getTotalPrice()));
        userService.updateAccount(account);
        AccountRecord record = new AccountRecord();
        record.setAccountId(accountId);
        record.setAmount(order.getTotalPrice().negate());
        record.setType(AccountRecord.Type.UNKNOWN);
        record.setUsername(account.getUsername());
        record.setNote("缴社保");
        userService.createAccountRecord(record);
        order.setStatus(Order.Status.PAYED);
        orderDao.save(order);

        // TODO 4. 提交快支付或银行处理


    }
}
