package com.mgj.core.company;

import com.mgj.base.socialinsurance.Company;
import com.mgj.core.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanqu on 2016/7/6.
 */
@Service
public class CompanyService extends BaseService<CompanyDao, Company, Long> {
    public List<Company> findByUsername(String username) {
        return dao.findByUsername(username);
    }
    public Page<Company> findByUsername(String username, Pageable pageable) {
        return dao.findByUsername(username, pageable);
    }
}
