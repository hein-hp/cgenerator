package org.hein.core.generator;

import cn.hutool.core.io.FileUtil;
import org.hein.config.ConfigurationFactory;
import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hein.common.Constant.EXTRA_PREFIX;

/**
 * Abstract Code Generator
 */
public abstract class AbstractCodeGenerator implements CodeGenerateFilter<List<TableInfo>> {

    public static final String AUTHOR = ConfigurationFactory.getInstance().getAsString(EXTRA_PREFIX + "author");
    public static final String JAVA_SUFFIX = ".java";

    public void handle(List<TableInfo> tableInfoList) {
        String packagePath = getPackagePath();
        for (TableInfo tableInfo : tableInfoList) {
            try {
                doGenerate(tableInfo,
                        FileUtil.getWriter(packagePath + tableInfo.getEntityName() + getClassNameSuffix() + JAVA_SUFFIX, StandardCharsets.UTF_8, false));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException;

    protected abstract String getPackagePath();

    protected abstract String getClassNameSuffix();

    protected String getPackagePath(String packageName) {
        return System.getProperty("user.dir") +
                "\\src\\main\\java\\" + packageName.replace(".", "\\") + "\\";
    }
}