package com.pearadmin.generator.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源相关信息
 *
 * @author Bamboo
 * @since 2020-09-30
 */
@Data
@Configuration
public class DbConfig {

    /**
     * JDBC 驱动
     */
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    /**
     * JDBC 账号
     */
    @Value("${spring.datasource.druid.master.username}")
    private String userName;

    /**
     * JDBC 密码
     */
    @Value("${spring.datasource.druid.master.password}")
    private String password;

    /**
     * JDBC url
     */
    @Value("${spring.datasource.druid.master.url}")
    private String url;

}
