package com.pearadmin.generator.mapper;

import com.pearadmin.generator.domain.FieldInfo;
import com.pearadmin.generator.domain.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码生成Mapper
 *
 * @author Bamboo
 * @since 2020-09-28
 */
@Mapper
public interface GeneratorMapper {

    /**
     * 获取数据库中的表
     *
     * @param tableName 表名
     * @param tableDescribe 表描述
     * @return tables
     */
    List<TableInfo> getTables(@Param("tableName") String tableName, @Param("tableDescribe") String tableDescribe);

    /**
     * 获取表所有字段
     *
     * @param tableName 表名
     * @return fields
     */
    List<FieldInfo> getFields(@Param("tableName") String tableName);
}
