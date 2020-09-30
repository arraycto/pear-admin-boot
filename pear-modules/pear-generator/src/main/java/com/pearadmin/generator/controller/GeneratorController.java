package com.pearadmin.generator.controller;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.tools.file.FileUtil;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.ResultTable;
import com.pearadmin.generator.bean.GeneratorInfo;
import com.pearadmin.generator.domain.FieldInfo;
import com.pearadmin.generator.domain.TableInfo;
import com.pearadmin.generator.service.GeneratorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成控制器
 *
 * @author Bamboo
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController extends BaseController {

    @Resource
    private GeneratorService generatorService;

    @GetMapping("getTables")
    public ResultTable getTables(PageDomain page, String tableName, String tableDescribe) {
        PageInfo<TableInfo> pageInfo = generatorService.getTables(page, tableName, tableDescribe);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    @GetMapping("getFields")
    public ResultTable getTables(PageDomain page, String tableName) {
        PageInfo<FieldInfo> pageInfo = generatorService.getFields(page, tableName);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    @PostMapping("execute")
    public void execute(HttpServletResponse response, @RequestBody GeneratorInfo generatorInfo) throws Exception {
        String path = generatorService.execute(generatorInfo);
        // 下载 丨 删除临时文件
        FileUtil.downloadFile(path + "code.zip","code.zip",true,response);
        FileUtil.deleteFile(path);
    }

}
