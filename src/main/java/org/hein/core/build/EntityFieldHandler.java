package org.hein.core.build;

import org.apache.commons.lang3.StringUtils;
import org.hein.core.annotation.Handler;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.jdbc.metadata.FieldInfo;
import org.hein.jdbc.metadata.TableInfo;

import java.util.List;

import static org.hein.common.Constant.UNDERLINE;
import static org.hein.jdbc.JdbcConstant.SQL_TO_JAVA_TYPE;

/**
 * Entity Field
 */
@Handler(order = 1, handlerType = HandlerTypeEnum.BUILD)
public class EntityFieldHandler implements BuildTableInfoFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(tableInfo -> tableInfo.getFieldInfoList().forEach(this::handleFieldName));
    }

    private void handleFieldName(FieldInfo fieldInfo) {
        String[] strs = fieldInfo.getFieldName().split(UNDERLINE);
        StringBuilder propertyName = new StringBuilder(strs[0]);
        if (strs.length > 1) {
            for (int i = 1; i < strs.length; i++) {
                propertyName.append(StringUtils.capitalize(strs[i]));
            }
        }
        fieldInfo.setPropertyName(propertyName.toString());
        fieldInfo.setJavaType(SQL_TO_JAVA_TYPE.get(fieldInfo.getSqlType()));
    }
}