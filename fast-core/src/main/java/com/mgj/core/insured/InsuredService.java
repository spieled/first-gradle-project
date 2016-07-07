package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanqu on 2016/6/29.
 */
@Service
public class InsuredService extends BaseService<InsuredPersonDao, InsuredPerson, Long> {

    private static Logger logger = LoggerFactory.getLogger(InsuredService.class);

    @Transactional
    public void updateStatus(long id, InsuredPerson.Status status) {
        dao.updateStatus(id, status);
    }
}
