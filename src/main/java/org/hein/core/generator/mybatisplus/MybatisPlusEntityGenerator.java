package org.hein.core.generator.mybatisplus;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractEntityGenerator;
import org.hein.jdbc.metadata.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.hein.common.Constant.ENTITY_PACKAGE;
import static org.hein.common.Constant.ENTITY_SUFFIX;

/**
 * MybatisPlus Entity
 */
@Handler(order = 0, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS_PLUS)
public class MybatisPlusEntityGenerator extends AbstractEntityGenerator {

    @Override
    protected void doGenerate(TableInfo tableInfo, BufferedWriter writer) throws IOException {
        writer.write("package " + ENTITY_PACKAGE + ";");
        writer.newLine();
        writer.newLine();
        writer.write("import com.baomidou.mybatisplus.annotation.TableName;");
        writer.newLine();
        writer.write("import lombok.Data;");
        writer.newLine();
        writer.newLine();
        writer.write("import java.io.Serial;");
        writer.newLine();
        writer.write("import java.io.Serializable;");
        writer.newLine();
        if (tableInfo.getHasDecimal().equals(Boolean.TRUE)) {
            writer.write("import java.math.BigDecimal;");
            writer.newLine();
        }
        if (tableInfo.getHasDate().equals(Boolean.TRUE) || tableInfo.getHasDateTime().equals(Boolean.TRUE)) {
            writer.write("import java.util.Date;");
            writer.newLine();
            writer.newLine();
        }
        writer.write("/**");
        writer.newLine();
        writer.write(" * " + tableInfo.getComment() + "实体");
        writer.newLine();
        writer.write(" * ");
        writer.newLine();
        writer.write(" * @author " + AUTHOR);
        writer.newLine();
        writer.write(" */");
        writer.newLine();
        writer.write("@Data");
        writer.newLine();
        writer.write("@TableName(\"" + tableInfo.getTableName() + "\")");
        writer.newLine();
        writer.write("public class " + tableInfo.getEntityName() + ENTITY_SUFFIX + " implements Serializable {");
        writer.newLine();
        writer.newLine();
        writer.write("    @Serial");
        writer.newLine();
        writer.write("    private static final long serialVersionUID = 1L;");
        writer.newLine();
        tableInfo.getFieldInfoList().forEach(fieldInfo -> {
            try {
                writer.newLine();
                writer.write("    /**");
                writer.newLine();
                writer.write("     * " + fieldInfo.getComment());
                writer.newLine();
                writer.write("     */");
                writer.newLine();
                writer.write("    private " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.write("}");
        writer.newLine();
        writer.flush();
    }
}