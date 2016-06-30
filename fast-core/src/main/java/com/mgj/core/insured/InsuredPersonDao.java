package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by yanqu on 2016/6/29.
 */
@Repository
public class InsuredPersonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_SQL = "create table insured_person(userId,username,company_id,`name`,id_number,city,city_name,`type`,status,note,on_station,insured,create_time,update_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_BY_ID_SQL = "select id,userId,username,company_id,name,id_number,city,city_name,`type`,status,note,on_station,insured,create_time,update_time from insured_person where id=?";
    private static final String FIND_BY_USERNAME_SQL = "select id,userId,username,company_id,name,id_number,city,city_name,`type`,status,note,on_station,insured,create_time,update_time from insured_person where username=?";
    private static final String DELETE_BY_ID_SQL = "delete from insured_person where id=?";
    private static final String UPDATE_SQL = "update insured_person set x=? where id=?"; // TODO

    public void create(InsuredPerson insuredPerson) {
        Date now = new Date();
        jdbcTemplate.update(CREATE_SQL, new Object[] {
                insuredPerson.getUserId(),
                insuredPerson.getUsername(),
                insuredPerson.getCompanyId(),
                insuredPerson.getName(),
                insuredPerson.getIdNumber(),
                insuredPerson.getCity(),
                insuredPerson.getCityName(),
                insuredPerson.getType().name(),
                insuredPerson.getStatus().name(),
                insuredPerson.getNote(),
                insuredPerson.isOnStation(),
                insuredPerson.isInsured(),
                now,
                now
        });

    }

    public InsuredPerson findById(long id) {
        List<InsuredPerson> list = jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new InsuredPersonMapper());
        return list.isEmpty() ? null : list.get(0);
    }

    public List<InsuredPerson> findByUsername(String username) {
        return jdbcTemplate.query(FIND_BY_USERNAME_SQL, new Object[] {username}, new InsuredPersonMapper());
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, new Object[] {id});

    }

    public void update(InsuredPerson insuredPerson) {

    }
}
