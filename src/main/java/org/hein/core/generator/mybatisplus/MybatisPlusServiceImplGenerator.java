package org.hein.core.generator.mybatisplus;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractServiceImplGenerator;
import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.*;

/**
 * MybatisPlus ServiceImpl
 */
@Handler(order = 3, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS_PLUS)
public class MybatisPlusServiceImplGenerator extends AbstractServiceImplGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        String entityName = tableInfo.getEntityName();
        writer.write("package " + SERVICE_IMPL_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;");
        writer.newLine();
        writer.write("import " + ENTITY_PACKAGE + "." + entityName + ENTITY_SUFFIX + ";");
        writer.newLine();
        writer.write("import " + MAPPER_PACKAGE + "." + entityName + MAPPER_SUFFIX + ";");
        writer.newLine();
        writer.write("import " + SERVICE_PACKAGE + "." + entityName + SERVICE_SUFFIX + ";");
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
        writer.write(String.format("public class %s extends ServiceImpl<%s, %s> implements %s {",
                entityName + SERVICE_IMPL_SUFFIX, entityName + MAPPER_SUFFIX, entityName + ENTITY_SUFFIX, entityName + SERVICE_SUFFIX));
        writer.newLine();
        writer.write("}");
        writer.flush();
    }
}