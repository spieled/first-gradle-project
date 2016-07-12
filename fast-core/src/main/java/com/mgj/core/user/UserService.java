package com.mgj.core.user;

import com.mgj.base.socialinsurance.*;
import com.mgj.core.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    private CompanyService companyService;
    @Autowired
    private OfflinePayRecordDao offlinePayRecordDao;
    @Autowired
    private AccountRecordDao accountRecordDao;

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
            profile.setNickName(username);
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
            List<Account> accounts = new ArrayList<>();
            Account account = new Account();
            account.setUsername(username);
            account.setName("个人账户");
            Account save = accountDao.save(account);
            accounts.add(save);
            List<Company> companies = companyService.findByUsername(username);
            if (companies != null && !companies.isEmpty()) {
                for (Company company : companies) {
                    account = new Account();
                    account.setName(company.getName() + "企业账户");
                    account.setUsername(username);
                    account.setCompanyId(company.getId());
                    account.setType(Account.Type.COMPANY);
                    accounts.add(accountDao.save(account));
                }
            }
            return accounts;
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

    @Transactional
    public void createOfflinePayRecord(OfflinePayRecord offlinePayRecord) {
        offlinePayRecordDao.save(offlinePayRecord);
    }

    public Page<OfflinePayRecord> findOfflinePayRecords(Pageable pageable) {
        return offlinePayRecordDao.findAll(pageable);
    }

    public Page<OfflinePayRecord> findOfflinePayRecords(long accountId, Pageable pageable) {
        return offlinePayRecordDao.findByAccountId(accountId, pageable);
    }

    @Transactional
    public void updateOfflinePayrecordStatus(long id, OfflinePayRecord.Status status) {
        offlinePayRecordDao.updateStatusById(id, status);
        if (status == OfflinePayRecord.Status.CHECK_SUCCESS) {
            // 更新余额
            OfflinePayRecord record = offlinePayRecordDao.findOne(id);
            BigDecimal amount = record.getAmount();
            long accountId = record.getAccountId();
            Account account = accountDao.findOne(accountId);
            account.setBalance(account.getBalance().add(amount));
            accountDao.save(account);

            // 添加账户变更记录
            AccountRecord accountRecord = new AccountRecord();
            accountRecord.setType(AccountRecord.Type.OFFLINE);
            accountRecord.setAccountId(accountId);
            accountRecord.setAmount(amount);
            accountRecord.setNote("上传打款凭证，审核通过");
            accountRecordDao.save(accountRecord);
        }
    }

    public Account findAccountById(long id) {
        return accountDao.findOne(id);
    }

    public Page<AccountRecord> findAccountRecords(long accountid, Pageable pageable) {
        return accountRecordDao.findByAccountId(accountid, pageable);
    }

    public OfflinePayRecord findOfflinePayRecordById(long id) {
        return offlinePayRecordDao.findOne(id);
    }

    @Transactional
    public void deleteOfflinePayRecord(long id) {
        offlinePayRecordDao.delete(id);
    }

    @Transactional
    public void updateAvatar(String username, String avatar) {
        profileDao.updateAvatarByUsername(username, avatar);
    }

    @Transactional
    public void updateProfileIdPic(String username, String idPositive, String idNegtive) {
        profileDao.updateIdPic(username, idPositive, idNegtive);

    }
}
