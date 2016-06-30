package com.mgj.core.insured;

import com.mgj.base.socialinsurance.InsuredPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/29.
 */
public class InsuredPersonMapper implements RowMapper<InsuredPerson> {

    public InsuredPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
        InsuredPerson obj = new InsuredPerson();
        obj.setId(rs.getLong("id"));
        obj.setUserId(rs.getLong("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setCompanyId(rs.getLong("company_id"));
        obj.setName(rs.getString("name"));
        obj.setIdNumber(rs.getString("id_number"));
        obj.setCity(rs.getInt("city"));
        obj.setCityName(rs.getString("city_name"));
        try {
            obj.setType(Enum.valueOf(InsuredPerson.Type.class, rs.getString("type")));
        } catch (Exception ignored) {}
        try {
            obj.setStatus(Enum.valueOf(InsuredPerson.Status.class, rs.getString("status")));
        }catch (Exception ignored) {}
        obj.setNote(rs.getString("note"));
        obj.setOnStation(rs.getBoolean("on_station"));
        obj.setInsured(rs.getBoolean("insured"));
        try {
            obj.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
        }catch (Exception ignored) {}
        try {
            obj.setUpdateTime(new Date(rs.getTimestamp("update_time").getTime()));
        } catch (Exception ignored) {}
        return obj;
    }
}
