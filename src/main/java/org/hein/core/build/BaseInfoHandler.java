package org.hein.core.build;

import org.hein.config.ConfigurationFactory;
import org.hein.core.annotation.Handler;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.jdbc.ConnectionFactory;
import org.hein.jdbc.metadata.FieldInfo;
import org.hein.jdbc.metadata.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hein.common.Constant.GENERATOR_PREFIX;

/**
 * Base Info
 */
@Handler(order = 0, handlerType = HandlerTypeEnum.BUILD)
public class BaseInfoHandler implements BuildTableInfoFilter<List<TableInfo>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInfoHandler.class);

    private static final String SHOW_TABLE_STATUS = "SHOW TABLE STATUS;";
    private static final String SHOW_FULL_FIELDS_FROM_TABLE = "SHOW FULL FIELDS FROM %s;";

    private static final Boolean GENERATE_ALL_TABLE =
            ConfigurationFactory.getInstance().getAsBoolean(GENERATOR_PREFIX + "generate-table.all");

    @Override
    public void handle(List<TableInfo> tableInfoList) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            handleTable(tableInfoList, connection);
            handleField(tableInfoList, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to access table", e);
        }
    }

    private void handleTable(List<TableInfo> tableInfoList, Connection conn) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SHOW_TABLE_STATUS);
             ResultSet rs = preparedStatement.executeQuery()) {
            if (Boolean.TRUE.equals(GENERATE_ALL_TABLE)) {
                while (rs.next()) {
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setTableName(rs.getString("Name"));
                    tableInfo.setComment(rs.getString("Comment"));
                    tableInfoList.add(tableInfo);
                }
            } else {
                HashSet<String> tableNames =
                        new HashSet<>(ConfigurationFactory.getInstance().getAsList(GENERATOR_PREFIX + "generate-table.names", String.class));
                while (rs.next()) {
                    String tableName = rs.getString("Name");
                    if (!tableNames.contains(tableName)) {
                        continue;
                    }
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setTableName(tableName);
                    tableInfo.setComment(rs.getString("Comment"));
                    tableInfoList.add(tableInfo);
                }
            }
        }
    }

    private void handleField(List<TableInfo> tableInfoList, Connection connection) throws SQLException {
        Map<String, TableInfo> map = tableInfoList.
                stream().collect(Collectors.toMap(TableInfo::getTableName, tableInfo -> tableInfo));
        map.forEach((tableName, tableInfo) -> {
            String sql = String.format(SHOW_FULL_FIELDS_FROM_TABLE, tableName);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet rs = preparedStatement.executeQuery()) {
                List<FieldInfo> fieldInfoList = new ArrayList<>();
                while (rs.next()) {
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setFieldName(rs.getString("Field"));
                    fieldInfo.setSqlType(removeSize(rs.getString("Type")));
                    fieldInfo.setComment(rs.getString("Comment"));
                    fieldInfoList.add(fieldInfo);
                }
                map.get(tableName).setFieldInfoList(fieldInfoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String removeSize(String type) {
        int index;
        if ((index = type.indexOf('(')) != -1) {
            return type.substring(0, index);
        }
        return type;
    }
}