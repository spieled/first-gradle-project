package com.mgj.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaoping Liu<spieled916@gmail.com> on 2016-11-07 16:11.
 */
public class Singleton {
    /**
     * 单个元素的List集合
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> list(T t) {
        List<T> list = new ArrayList<>(1);
        list.add(t);
        return list;
    }
}
