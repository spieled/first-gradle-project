package com.mgj.core.user;

import com.mgj.base.socialinsurance.Account;
import com.mgj.base.socialinsurance.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanqu on 2016/7/7.
 */
@Service
public class UserService {
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void updateProfile(Profile profile) {
        profileDao.save(profile);
    }

    public Profile findProfileById(long id) {
        return profileDao.findOne(id);
    }

    public Profile findProfileByUsername(String username) {
        List<Profile> list = profileDao.findByUsername(username);
        if (list == null || list.isEmpty()) {
            Profile profile = new Profile();
            profile.setUsername(username);
            return profile;
        }
        return list.get(0);
    }

    @Transactional
    public void updateAccount(Account account) {
        accountDao.save(account);
    }

    @Transactional
    public List<Account> findAccountByUsername(String username) {
        List<Account> list = accountDao.findByUsername(username);
        if (list == null || list.isEmpty()) {
            Account account = new Account();
            account.setUsername(username);
            Account save = accountDao.save(account);
            return Arrays.asList(save);
        }
        return list;
    }

    @Transactional
    public Account findAccountByUsernameAndCompanyId(String username, long companyId) {
        List<Account> list = accountDao.findByUsernameAndCompanyId(username, companyId);
        if (list == null || list.isEmpty()) {
            Account account = new Account();
            account.setUsername(username);
            account.setCompanyId(companyId);
            return accountDao.save(account);
        }
        return list.get(0);
    }


}
