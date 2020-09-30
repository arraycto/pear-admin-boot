package com.pearadmin.system.param;

import lombok.Data;

/**
 * Describe: 查询角色接口参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class QueryRoleParam {

    /**
     * 角色标识
     * */
    private String roleCode;

    /**
     * 角色名称
     * */
    private String roleName;
}
