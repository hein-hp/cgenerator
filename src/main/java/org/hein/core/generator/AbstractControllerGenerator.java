package org.hein.core.generator;

import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.CONTROLLER_PACKAGE;
import static org.hein.common.Constant.CONTROLLER_SUFFIX;

/**
 * Abstract Controller Generator
 */
public abstract class AbstractControllerGenerator extends AbstractCodeGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + CONTROLLER_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import org.springframework.web.bind.annotation.RestController;");
        writer.newLine();
        writer.newLine();
        writer.write("/**");
        writer.newLine();
        writer.write(" * " + tableInfo.getComment() + "控制层");
        writer.newLine();
        writer.write(" * ");
        writer.newLine();
        writer.write(" * @author " + AUTHOR);
        writer.newLine();
        writer.write(" */");
        writer.newLine();
        writer.write("@RestController");
        writer.newLine();
        writer.write("public class " + tableInfo.getEntityName() + CONTROLLER_SUFFIX + " {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }

    @Override
    protected String getPackagePath() {
        return getPackagePath(CONTROLLER_PACKAGE);
    }

    @Override
    protected String getClassNameSuffix() {
        return CONTROLLER_SUFFIX;
    }
}