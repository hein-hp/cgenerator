package org.hein.core.generator;

import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.MAPPER_PACKAGE;
import static org.hein.common.Constant.MAPPER_SUFFIX;

/**
 * Abstract Mapper Generator
 */
public abstract class AbstractMapperGenerator extends AbstractCodeGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + MAPPER_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import org.apache.ibatis.annotations.Mapper;");
        writer.newLine();
        writer.newLine();
        writer.write("/**");
        writer.newLine();
        writer.write(" * " + tableInfo.getComment() + "持久层");
        writer.newLine();
        writer.write(" * ");
        writer.newLine();
        writer.write(" * @author " + AUTHOR);
        writer.newLine();
        writer.write(" */");
        writer.newLine();
        writer.write("@Mapper");
        writer.newLine();
        writer.write("public interface " + tableInfo.getEntityName() + MAPPER_SUFFIX + " {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }

    @Override
    protected String getPackagePath() {
        return getPackagePath(MAPPER_PACKAGE);
    }

    @Override
    protected String getClassNameSuffix() {
        return MAPPER_SUFFIX;
    }
}