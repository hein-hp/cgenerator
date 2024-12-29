package org.hein.core.build;

import org.apache.commons.lang3.StringUtils;
import org.hein.config.ConfigurationFactory;
import org.hein.core.annotation.Handler;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.jdbc.metadata.TableInfo;

import java.util.List;

import static org.hein.common.Constant.GENERATOR_PREFIX;

/**
 * Entity Name
 */
@Handler(order = 2, handlerType = HandlerTypeEnum.BUILD)
public class EntityNameHandler implements BuildTableInfoFilter<List<TableInfo>> {

    private static final boolean IGNORE_TABLE_PREFIX =
            ConfigurationFactory.getInstance().getAsBoolean(GENERATOR_PREFIX + "ignore-table-prefix.ignore");
    private static final List<String> IGNORE_TABLE_PREFIX_VALUES =
            ConfigurationFactory.getInstance().getAsList(GENERATOR_PREFIX + "ignore-table-prefix.values", String.class);

    @Override
    public void handle(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(this::handleTableName);
    }

    private void handleTableName(TableInfo tableInfo) {
        String tableName = tableInfo.getTableName();
        if (IGNORE_TABLE_PREFIX) {
            for (String prefix : IGNORE_TABLE_PREFIX_VALUES) {
                if (tableName.startsWith(prefix)) {
                    tableName = tableName.substring(prefix.length());
                }
            }
        }
        String[] names = tableName.split("_");
        StringBuilder entityName = new StringBuilder();
        for (String name : names) {
            entityName.append(StringUtils.capitalize(name));
        }
        tableInfo.setEntityName(entityName.toString());
    }
}