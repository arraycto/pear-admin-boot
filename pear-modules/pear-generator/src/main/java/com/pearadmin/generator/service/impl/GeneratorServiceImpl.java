package com.pearadmin.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.generator.bean.ExtTemplates;
import com.pearadmin.generator.bean.GeneratorInfo;
import com.pearadmin.generator.constant.GeneratorConst;
import com.pearadmin.generator.core.Generator;
import com.pearadmin.generator.core.config.DbConfig;
import com.pearadmin.generator.domain.FieldInfo;
import com.pearadmin.generator.domain.TableInfo;
import com.pearadmin.generator.mapper.GeneratorMapper;
import com.pearadmin.generator.service.GeneratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成Service 实现类
 *
 * @author Bamboo
 * @since 2020-09-28
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private GeneratorMapper generatorMapper;

    @Resource
    private DbConfig dbConfig;

    @Override
    public PageInfo<TableInfo> getTables(PageDomain page, String tableName, String tableDescribe) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<TableInfo> list = generatorMapper.getTables(tableName, tableDescribe);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<FieldInfo> getFields(PageDomain page, String tableName) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<FieldInfo> list = generatorMapper.getFields(tableName);
        return new PageInfo<>(list);
    }

    @Override
    public String execute(GeneratorInfo generatorInfo) throws Exception {
        generatorInfo.setDataSource(dbConfig);
        // 判断是否生成页面
        List<ExtTemplates> templatesList = new ArrayList<>();
        if(generatorInfo.getCreatePage()) {
            templatesList.add(new ExtTemplates("html","/templates/ftl/page.html", "", GeneratorConst.DOT_HTML));
            templatesList.add(new ExtTemplates("html","/templates/ftl/page_add.html", "_add", GeneratorConst.DOT_HTML));
            templatesList.add(new ExtTemplates("html","/templates/ftl/page_edit.html","_edit", GeneratorConst.DOT_HTML));
        }
        generatorInfo.setExtendTemplate(templatesList);
        // 构建生成器
        Generator generator = Generator.builder(generatorInfo);
        return generator.execute();
    }

}
