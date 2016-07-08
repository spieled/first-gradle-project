package com.mgj.core.user;

import com.mgj.base.socialinsurance.Account;
import com.mgj.core.base.BaseDao;

import java.util.List;

/**
 * Created by yanqu on 2016/7/8.
 */
public interface AccountDao extends BaseDao<Account, Long> {
    List<Account> findByUsernameAndCompanyId(String username, long companyId);
}
