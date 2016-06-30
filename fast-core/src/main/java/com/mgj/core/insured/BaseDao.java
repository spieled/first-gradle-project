package com.mgj.core.insured;

import com.mgj.base.AutoIncre;
import com.mgj.base.BaseEntity;
import com.mgj.base.Col;
import com.mgj.util.Util;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqu on 2016/6/30.
 */
public abstract class BaseDao<T extends BaseEntity> {

    @Autowired
    SqlSession sqlSession;

     Logger logger = LoggerFactory.getLogger(BaseDao.class);
     Class<T> clazz;
     String className;
     String tableName;
     String insertTemplate = "insert into %s (%s) values (%s)";
     String selectTemplate = "select %s,%s from %s where %s=#{id}";
     String deleteTemplate = "delete from %s where %s=#{id}";
     String insertSql;
     String selectSql;
     String deleteSql;
     List<String> fieldList = new ArrayList<>();
     String pk = "id";

    public void create(@Param("entity") T entity) {
        sqlSession.insert(insertSql, entity);
    }

    public BaseDao() {
        // 使用反射技术得到T的真实类型
        // 获取当前new的对象的 泛型的父类 类型
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        className = clazz.getSimpleName();
        tableName = Util.lowerFirstChar(clazz.getSimpleName());

        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, Col.class);
        StringBuilder builder = new StringBuilder();
        StringBuilder builderValues = new StringBuilder();

        for (Field field : fields) {
            String fieldName = field.getName();
            fieldList.add(fieldName);
            if (field.getAnnotation(AutoIncre.class) != null) {
                pk = fieldName;
                continue;
            }
            builder.append(fieldName).append(",");
            builderValues.append("#{").append(fieldName).append("},");
//            builderValues.append("#{entity.").append(fieldName).append("},");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        if (builderValues.length() > 0) {
            builderValues.deleteCharAt(builderValues.length() - 1);
        }

        insertSql = String.format(insertTemplate, tableName, builder.toString(), builderValues.toString());
        selectSql = String.format(selectTemplate, pk, builder.toString(), tableName, pk);
        deleteSql = String.format(deleteTemplate, tableName, pk);
        logger.debug(insertSql);
        logger.debug(selectSql);
        logger.debug(deleteSql);
    }

}
