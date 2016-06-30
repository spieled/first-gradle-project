package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanqu on 2016/6/29.
 */
@Service
public class InsuredService implements InitializingBean {
    @Autowired
    private InsuredPersonDao insuredPersonDao;
    @Autowired
    private InsureMapper insureMapper;

    public void create(InsuredPerson insuredPerson) {
        insuredPersonDao.create(insuredPerson);
    }
    public InsuredPerson findById(long id) {
        return insuredPersonDao.findById(id);
    }
    public List<InsuredPerson> findByUsername(String username) {
        return insuredPersonDao.findByUsername(username);
    }
    public void deleteById(long id) {
        insuredPersonDao.deleteById(id);
    }


    public void afterPropertiesSet() throws Exception {

        InsuredPerson person = new InsuredPerson();
        person.setName("张三");
        try {
            insureMapper.create(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
