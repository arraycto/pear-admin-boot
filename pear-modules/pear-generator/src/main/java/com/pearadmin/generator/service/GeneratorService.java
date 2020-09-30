package com.pearadmin.generator.service;


import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.generator.bean.GeneratorInfo;
import com.pearadmin.generator.domain.FieldInfo;
import com.pearadmin.generator.domain.TableInfo;

/**
 * 代码生成Service 接口
 *
 * @author Bamboo
 * @since 2020-09-28
 */
public interface GeneratorService {

    /**
     * 获取数据库中的表
     *
     * @param tableName 表名
     * @param tableDescribe 表描述
     * @return page data for the tables
     */
    PageInfo<TableInfo> getTables(PageDomain page, String tableName, String tableDescribe);

    /**
     * 获取表所有字段
     *
     * @param tableName 表名
     * @return page data for the fields
     */
    PageInfo<FieldInfo> getFields(PageDomain page, String tableName);

    /**
     * 生成代码 丨 返回存放路径
     *
     * @param generatorInfo 代码生成相关信息
     * @return file path
     */
    String execute(GeneratorInfo generatorInfo) throws Exception;
}
