package com.mgj.core.util;

import com.mgj.base.AutoIncre;
import com.mgj.base.Col;
import com.mgj.base.Constants;
import com.mgj.util.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yanqu on 2016/6/30.
 */
public class MybatisUtil {

    Logger logger = LoggerFactory.getLogger(MybatisUtil.class);

    String insertTemplate = "insert into %s (%s) values (%s)";
    String selectTemplate = "select %s,%s from %s where %s=#{id}";
    String deleteTemplate = "delete from %s where %s=#{id}";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new MybatisUtil().generateDao("com.mgj");
    }

    public void generateDao(String basePackage) throws ClassNotFoundException, IOException {
        for (Class clazz : scanEntityClasses(basePackage)) {
            generateDaoFile(generateSql(clazz));
        }
    }

    public List<Class> scanEntityClasses(String basePackage) throws ClassNotFoundException {
        return SpringClassScanner.getInstance().findTableAnnotatedClassed(basePackage);
    }

    public SqlEntity generateSql(Class clazz) {
        String pk = "id";
        String insertSql;
        String selectSql;
        String deleteSql;
        String className = clazz.getName();
        String tableName = Util.lowerFirstChar(clazz.getSimpleName());

        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, Col.class);
        StringBuilder builder = new StringBuilder();
        StringBuilder builderValues = new StringBuilder();

        for (Field field : fields) {
            String fieldName = field.getName();
            if (field.getAnnotation(AutoIncre.class) != null) {
                pk = fieldName;
                continue;
            }
            builder.append(fieldName).append(",");
            builderValues.append("#{entity.").append(fieldName).append("},");
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
        return new SqlEntity(pk, className, tableName, insertSql, selectSql, deleteSql, "");
    }

    public void generateDaoFile(SqlEntity sqlEntity) throws IOException {
        StringBuilder builder = new StringBuilder();
        String fileName = String.format("%sDao.java", sqlEntity.getClassSimpleName());
        String classSimpleName = sqlEntity.getClassSimpleName();
        builder
                .append("import ").append(sqlEntity.getClassName()).append(";")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Insert;")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Update;")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Delete;")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Mapper;")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Param;")
                .append(Constants.NEWLINE)
                .append("import org.apache.ibatis.annotations.Select;")
                .append(Constants.NEWLINE)
                .append("@Mapper")
                .append(Constants.NEWLINE)
                .append("public interface "+ classSimpleName +"Dao {")
                .append(Constants.NEWLINE)
                .append("    @Select(\""+sqlEntity.getSelectSql()+"\")")
                .append(Constants.NEWLINE)
                .append("    public "+ classSimpleName +" findById(@Param(\"id\") long id);")
                .append(Constants.NEWLINE)
                .append("    @Insert(\""+sqlEntity.getInsertSql()+"\")")
                .append(Constants.NEWLINE)
                .append("    public void create(@Param(\"entity\") "+ classSimpleName +" entity);")
                .append(Constants.NEWLINE)
                .append("    @Delete(\""+sqlEntity.getDeleteSql()+"\")")
                .append(Constants.NEWLINE)
                .append("    public void deleteById(@Param(\"id\") " + classSimpleName + " id);")
                .append(Constants.NEWLINE)
                .append("    @Update(\""+sqlEntity.getUpdateSql()+"\")")
                .append(Constants.NEWLINE)
                .append("    public void update(@Param(\"entity\") "+ classSimpleName +" entity);")
                .append(Constants.NEWLINE)
                .append("}");

        logger.debug(builder.toString());

        FileUtils.writeStringToFile(new File(fileName), builder.toString(), Constants.UFT_8);
    }

}
