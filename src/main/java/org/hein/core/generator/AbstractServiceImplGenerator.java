package org.hein.core.generator;

import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.*;

/**
 * Abstract ServiceImpl Generator
 */
public abstract class AbstractServiceImplGenerator extends AbstractCodeGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        String entityName = tableInfo.getEntityName();
        writer.write("package " + SERVICE_IMPL_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import " + SERVICE_PACKAGE + "." + entityName + "Service;");
        writer.newLine();
        writer.write("import org.springframework.stereotype.Service;");
        writer.newLine();
        writer.newLine();
        writer.write("/**");
        writer.newLine();
        writer.write(" * " + tableInfo.getComment() + "接口实现层");
        writer.newLine();
        writer.write(" * ");
        writer.newLine();
        writer.write(" * @author " + AUTHOR);
        writer.newLine();
        writer.write(" */");
        writer.newLine();
        writer.write("@Service");
        writer.newLine();
        writer.write("public class " + entityName + SERVICE_IMPL_SUFFIX + " implements " + entityName + "Service {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }

    @Override
    protected String getPackagePath() {
        return getPackagePath(SERVICE_IMPL_PACKAGE);
    }

    @Override
    protected String getClassNameSuffix() {
        return SERVICE_IMPL_SUFFIX;
    }
}