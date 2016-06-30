package com.mgj.core.insured;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by yanqu on 2016/6/30.
 */
@Mapper
public interface CityMapper {
    String var = "";
    @Select("select * "+var+"from city where id=#{id}")
    public City findById(@Param("id") long id);

    @Insert("insert into city(name, level, createTime) values (#{city.name}, #{city.level}, now())")
    void insert(@Param("city") City city);

}
