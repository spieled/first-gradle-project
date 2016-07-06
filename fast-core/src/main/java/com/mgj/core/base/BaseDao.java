package com.mgj.core.base;

import com.mgj.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yanqu on 2016/7/4.
 */
@MappedSuperclass
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    List<T> findByUsername(String username);
    Page<T> findByUsername(String username, Pageable pageable);
}
