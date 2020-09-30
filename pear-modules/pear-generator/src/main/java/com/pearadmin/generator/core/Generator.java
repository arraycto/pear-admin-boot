package com.pearadmin.generator.core;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.pearadmin.common.tools.file.CompressFileUtil;
import com.pearadmin.common.tools.file.FileUtil;
import com.pearadmin.generator.bean.GeneratorInfo;
import com.pearadmin.generator.core.config.GeneratorConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

/**
 * FastCoding代码生成器
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
@Slf4j
public class Generator {

    private GeneratorInfo generatorInfo;

    /**
     * 构建一个Generator对象
     *
     * @param generatorInfo 生成信息
     * @return this
     */
    public static Generator builder(GeneratorInfo generatorInfo) {
        Generator generator = new Generator();
        generator.setGeneratorInfo(generatorInfo);
        return generator;
    }

    /**
     * 执行生成代码
     *
     * @return 临时路径
     * @throws Exception an exception may occur when operating the file stream
     */
    public String execute() throws Exception {
        String tmpdir = System.getProperty("java.io.tmpdir") + "fastCoding." + IdWorker.getIdStr();
        generatorInfo.setTempPath(tmpdir);
        // 配置MybatisPlus,并执行生成
        AutoGenerator autoGenerator = this.setMybatisPlusConfig();
        autoGenerator.execute();
        log.info("===> 代码生成完毕! 正在努力压缩文件中...");
        // 生成完成进行压缩处理
        if(FileUtil.validateFileDir(tmpdir)) {
            FileOutputStream fos = new FileOutputStream(new File(tmpdir + "code.zip"));
            CompressFileUtil.toZip(tmpdir,"fastCoding_" + generatorInfo.getModuleName(),fos,true);
        }
        return tmpdir;
    }

    /**
     * mybatis plus 生成代码配置
     *
     * @return {@link AutoGenerator}
     */
    private AutoGenerator setMybatisPlusConfig() {
        AutoGenerator generator = new AutoGenerator();
        GeneratorConfig config = new GeneratorConfig().initConfig(generatorInfo);
        generator.setGlobalConfig(config.initGlobalConfig());
        generator.setDataSource(config.initDataSourceConfig());
        generator.setStrategy(config.initStrategyConfig());
        generator.setPackageInfo(config.initPackageConfig());
        generator.setTemplate(config.initTemplateConfig());
        generator.setTemplateEngine(config.initTemplateEngine());
        return generator;
    }
}
