package cloud.database;

public enum ColumnType {
    // 数值类型
    INTEGER("NUMBER(10)", "INT"),
    BIGINT("NUMBER(19)", "BIGINT"),
    DECIMAL("NUMBER(19,2)", "DECIMAL(19,2)"),

    // 字符类型
    VARCHAR("VARCHAR2", "VARCHAR"),
    TEXT("CLOB", "TEXT"),
    CHAR("CHAR", "CHAR"),

    // 日期时间类型
    DATE("DATE", "DATE"),
    TIMESTAMP("TIMESTAMP", "TIMESTAMP"),

    // 二进制类型
    BLOB("BLOB", "BLOB"),
    CLOB("CLOB", "CLOB");

    private final String oracleType;
    private final String dmType;

    ColumnType(String oracleType, String dmType) {
        this.oracleType = oracleType;
        this.dmType = dmType;
    }

    public String getType(DatabaseType dbType) {
        // 将 switch 表达式改为传统的 switch 语句
        switch (dbType) {
            case ORACLE:
                return this.oracleType;
            case DM8:
                return this.dmType;
            default:
                throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }

    /**
     * 获取带长度的类型
     * @param dbType 数据库类型
     * @param length 长度
     * @return 完整的类型字符串
     */
    public String getType(DatabaseType dbType, int length) {
        String baseType = getType(dbType);
        if (this == VARCHAR || this == CHAR) {
            return baseType + "(" + length + ")";
        }
        return baseType;
    }
}
