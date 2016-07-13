package com.mgj.core.order;

import com.mgj.base.socialinsurance.Order;
import com.mgj.base.socialinsurance.OrderItem;
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
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao itemDao;


    @Transactional
    public void createOrder(Order order, List<OrderItem> items) {
        // TODO 验证投保信息
        // TODO 扣除费用
        // TODO 保存投保信息

    }

    public Page<Order> findOrdersByUsername(String username, Pageable pageable) {
        return orderDao.findByUsername(username, pageable);
    }
}
