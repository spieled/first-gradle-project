package com.mgj.admin.base;

import com.mgj.util.Util;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanqu on 2016/7/5.
 */
public class BaseController {
    public Pageable getPageable(HttpServletRequest request) {
        int page = Util.parseInt(request, "page", 0); // 注意：page是从0开始的
        int size = Util.parseInt(request, "size", 1); // TODO 测试环境中size设置小一点，方便看效果。上线之前请改为正常size
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        return new PageRequest(page, size, sort);

    }
}
