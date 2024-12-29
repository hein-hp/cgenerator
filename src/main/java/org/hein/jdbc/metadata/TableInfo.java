package org.hein.jdbc.metadata;

import java.util.List;

/**
 * Table Info
 */
public class TableInfo {

    /**
     * Table Name
     */
    private String tableName;

    /**
     * Entity Name
     */
    private String entityName;

    /**
     * Comment
     */
    private String comment;

    /**
     * Field Info List
     */
    private List<FieldInfo> fieldInfoList;

    /**
     * Has Date
     */
    private Boolean hasDate;

    /**
     * Has DateTime
     */
    private Boolean hasDateTime;

    /**
     * Has Decimal
     */
    private Boolean hasDecimal;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    public Boolean getHasDate() {
        return hasDate;
    }

    public void setHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
    }

    public Boolean getHasDateTime() {
        return hasDateTime;
    }

    public void setHasDateTime(Boolean hasDateTime) {
        this.hasDateTime = hasDateTime;
    }

    public Boolean getHasDecimal() {
        return hasDecimal;
    }

    public void setHasDecimal(Boolean hasDecimal) {
        this.hasDecimal = hasDecimal;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", entityName='" + entityName + '\'' +
                ", comment='" + comment + '\'' +
                ", fieldInfoList=" + fieldInfoList +
                ", hasDate=" + hasDate +
                ", hasDateTime=" + hasDateTime +
                ", hasDecimal=" + hasDecimal +
                '}';
    }
}