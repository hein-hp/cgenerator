package org.hein.core.build;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.jdbc.metadata.TableInfo;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Extra Info
 */
@Handler(order = 3, handlerType = HandlerTypeEnum.BUILD)
public class ExtraInfoHandler implements BuildTableInfoFilter<List<TableInfo>> {

    private static final Set<String> HAS_DATE = Set.of("date", "datetime");
    private static final Set<String> HAS_DATETIME = Set.of("time", "timestamp");
    private static final Set<String> HAS_DECIMAL = Set.of("decimal");

    @Override
    public void handle(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(tableInfo -> tableInfo.getFieldInfoList().forEach(fieldInfo -> {
            setFlagIfContains(tableInfo::setHasDate, HAS_DATE, fieldInfo.getSqlType());
            setFlagIfContains(tableInfo::setHasDateTime, HAS_DATETIME, fieldInfo.getSqlType());
            setFlagIfContains(tableInfo::setHasDecimal, HAS_DECIMAL, fieldInfo.getSqlType());
        }));
    }

    private void setFlagIfContains(Consumer<Boolean> setter, Set<String> types, String sqlType) {
        if (types.contains(sqlType.toLowerCase())) {
            setter.accept(true);
        }
    }
}