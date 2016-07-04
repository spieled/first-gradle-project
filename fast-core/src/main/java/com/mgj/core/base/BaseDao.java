package com.mgj.core.base;

import com.mgj.base.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by yanqu on 2016/7/4.
 */
@MappedSuperclass
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
}
