package com.mgj.core.util;

import com.mgj.base.Constants;

/**
 * Created by yanqu on 2016/6/30.
 */
public class SqlEntity {
    private String pk;
    private String className;
    private String classSimpleName;
    private String tableName;
    private String insertSql;
    private String selectSql;
    private String deleteSql;
    private String updateSql;

    public SqlEntity(String pk, String className, String tableName, String insertSql, String selectSql, String deleteSql, String updateSql) {
        this.pk = pk;
        this.className = className;
        this.tableName = tableName;
        this.insertSql = insertSql;
        this.selectSql = selectSql;
        this.deleteSql = deleteSql;
        this.updateSql = updateSql;
        this.classSimpleName = className.substring(className.lastIndexOf(Constants.DOT)+1, className.length());
    }

    public String getPk() {
        return pk;
    }

    public String getClassName() {
        return className;
    }

    public String getTableName() {
        return tableName;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public String getDeleteSql() {
        return deleteSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public String getClassSimpleName() {
        return classSimpleName;
    }
}
