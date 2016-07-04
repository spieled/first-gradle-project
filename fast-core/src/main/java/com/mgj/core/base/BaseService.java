package com.mgj.core.base;

import com.mgj.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by yanqu on 2016/7/4.
 */
@MappedSuperclass
public class BaseService<DAO extends BaseDao<T,ID>, T extends BaseEntity, ID extends Serializable> {
    @Autowired
    protected DAO dao;

    public void create(T t) {
        dao.save(t);
    }

    public T findOne(ID id) {
        return dao.findOne(id);
    }

    public Iterable<T> save(Iterable<T> entities) {
        return dao.save(entities);
    }

    public boolean exists(ID id) {
        return dao.exists(id);
    }

    public Iterable<T> findAll() {
        return dao.findAll();
    }

    public Iterable<T> findAll(Iterable<ID> ids) {
        return dao.findAll(ids);
    }

    public long count() {
        return dao.count();
    }

    public void delete(ID id) {
        dao.delete(id);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

    public void delete(Iterable<? extends T> entities) {
        dao.delete(entities);
    }

    // 这个方法风险太高，暂不提供
    // void deleteAll();

    public Iterable<T> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

}
