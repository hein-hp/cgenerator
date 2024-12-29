package org.hein.jdbc.metadata;

/**
 * Field Info
 */
public class FieldInfo {

    /**
     * Field Name
     */
    private String fieldName;

    /**
     * Property Name
     */
    private String propertyName;

    /**
     * SQL Type
     */
    private String sqlType;

    /**
     * Java Type
     */
    private String javaType;

    /**
     * Comment
     */
    private String comment;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", javaType='" + javaType + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}