package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.base.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yanqu on 2016/7/4.
 */
interface InsuredPersonDao extends BaseDao<InsuredPerson, Long> {
    List<InsuredPerson> findByUsername(String username);
    Page<InsuredPerson> findAllByUsername(String username, Pageable pageable);
}
