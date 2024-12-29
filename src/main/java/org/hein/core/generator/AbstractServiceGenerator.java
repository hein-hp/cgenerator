package org.hein.core.generator;

import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.SERVICE_PACKAGE;
import static org.hein.common.Constant.SERVICE_SUFFIX;

/**
 * Abstract Service Generator
 */
public abstract class AbstractServiceGenerator extends AbstractCodeGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + SERVICE_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("/**");
        writer.newLine();
        writer.write(" * " + tableInfo.getComment() + "接口层");
        writer.newLine();
        writer.write(" * ");
        writer.newLine();
        writer.write(" * @author " + AUTHOR);
        writer.newLine();
        writer.write(" */");
        writer.newLine();
        writer.write("public interface " + tableInfo.getEntityName() + SERVICE_SUFFIX + " {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }

    @Override
    protected String getPackagePath() {
        return getPackagePath(SERVICE_PACKAGE);
    }

    @Override
    protected String getClassNameSuffix() {
        return SERVICE_SUFFIX;
    }
}