package cloud.database;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColumnDefinition {

    private String scheme;
    private String tableName;
    private String columnId;
    private String index;
    private String defaultValue;

    private String label;
    private String name;
    private ColumnType type;
    private Integer length;
    private boolean isPrimaryKey;
    private boolean notNull;
    private DatabaseType dbType;

    public ColumnDefinition(String name, ColumnType type, DatabaseType dbType) {
        this.name = name;
        this.type = type;
        this.dbType = dbType;
    }

    public ColumnDefinition(String name, ColumnType type, int length, DatabaseType dbType) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.dbType = dbType;
    }

    public String getTypeString() {
        return length != null ? type.getType(dbType, length) : type.getType(dbType);
    }

    public boolean isPrimaryKey() { return isPrimaryKey; }
    public boolean isNotNull() { return notNull; }
    public String getLabel() { return label; }
    public DatabaseType getDbType() { return dbType; }

    public ColumnDefinition primaryKey() {
        this.isPrimaryKey = true;
        return this;
    }

    public ColumnDefinition notNull() {
        this.notNull = true;
        return this;
    }

    public ColumnDefinition comment(String label) {
        this.label = label;
        return this;
    }
}
