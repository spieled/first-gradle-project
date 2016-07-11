package com.mgj.core.user;

import com.mgj.base.socialinsurance.OfflinePayRecord;
import com.mgj.core.base.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yanqu on 2016/7/11.
 */
public interface OfflinePayRecordDao extends BaseDao<OfflinePayRecord, Long> {
    @Modifying
    @Query("update OfflinePayRecord as o set o.status=?2 where o.id=?1")
    void updateStatusById(long id, OfflinePayRecord.Status status);

    Page<OfflinePayRecord> findByAccountId(long accountId, Pageable pageable);
}
