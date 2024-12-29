package org.hein.core.generator.mybatisplus;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractServiceGenerator;
import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.*;

/**
 * MybatisPlus Service
 */
@Handler(order = 2, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS_PLUS)
public class MybatisPlusServiceGenerator extends AbstractServiceGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + SERVICE_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import com.baomidou.mybatisplus.extension.service.IService;");
        writer.newLine();
        writer.write("import " + ENTITY_PACKAGE + "." + tableInfo.getEntityName() + ENTITY_SUFFIX + ";");
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
        writer.write("public interface " + tableInfo.getEntityName() + SERVICE_SUFFIX + " extends IService<" + tableInfo.getEntityName() + ENTITY_SUFFIX + "> {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }
}