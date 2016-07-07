package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.base.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yanqu on 2016/7/4.
 */
interface InsuredPersonDao extends BaseDao<InsuredPerson, Long> {
    @Modifying
    @Query("update InsuredPerson as p set p.status=?2 where p.id=?1")
    void updateStatus(long id, InsuredPerson.Status status);
}
