package org.hein.core.generator.mybatisplus;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractMapperGenerator;
import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.*;

/**
 * MybatisPlus Mapper
 */
@Handler(order = 1, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS_PLUS)
public class MybatisPlusMapperGenerator extends AbstractMapperGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + MAPPER_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import com.baomidou.mybatisplus.core.mapper.BaseMapper;");
        writer.newLine();
        writer.write("import org.apache.ibatis.annotations.Mapper;");
        writer.newLine();
        writer.write("import " + ENTITY_PACKAGE + "." + tableInfo.getEntityName() + ENTITY_SUFFIX + ";");
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
        writer.write("public interface " + tableInfo.getEntityName() + MAPPER_SUFFIX + " extends BaseMapper<" + tableInfo.getEntityName() + ENTITY_SUFFIX + "> {");
        writer.newLine();
        writer.write("}");
        writer.flush();
    }
}