package com.mgj.core.user;

import com.mgj.base.socialinsurance.AccountRecord;
import com.mgj.core.base.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yanqu on 2016/7/11.
 */
public interface AccountRecordDao extends BaseDao<AccountRecord, Long> {
    Page<AccountRecord> findByAccountId(long accountid, Pageable pageable);
}
