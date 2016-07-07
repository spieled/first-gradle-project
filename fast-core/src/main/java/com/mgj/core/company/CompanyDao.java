package com.mgj.core.company;

import com.mgj.base.socialinsurance.Company;
import com.mgj.core.base.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yanqu on 2016/7/4.
 */
interface CompanyDao extends BaseDao<Company, Long> {
    @Modifying
    @Query("update Company as c set c.status=?2 where c.id=?1")
    void updateStatus(long id, Company.Status status);

}
