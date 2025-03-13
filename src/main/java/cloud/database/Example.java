package cloud.database;

import java.util.Arrays;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        // 选择数据库类型
        DatabaseType dbType = DatabaseType.DM8;  // 或 DatabaseType.ORACLE
        
        String url = dbType == DatabaseType.DM8 
            ? "jdbc:dm://localhost:5236"
            : "jdbc:oracle:thin:@localhost:1521:orcl";
            
        String username = "SYSDBA";
        String password = "SYSDBA";

        try {
            // 加载驱动
            if (dbType == DatabaseType.DM8) {
                Class.forName("dm.jdbc.driver.DmDriver");
            } else {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            
            TableCreationUtil util = new TableCreationUtil(url, username, password, dbType);
            
            // 定义表结构，添加字段注释
            List<ColumnDefinition> columns = Arrays.asList(
                new ColumnDefinition("EMP_ID", ColumnType.BIGINT, dbType)
                    .primaryKey()
                    .comment("员工编号"),
                new ColumnDefinition("EMP_NAME", ColumnType.VARCHAR, 50, dbType)
                    .notNull()
                    .comment("员工姓名"),
                new ColumnDefinition("EMP_AGE", ColumnType.INTEGER, dbType)
                    .comment("员工年龄"),
                new ColumnDefinition("EMP_SALARY", ColumnType.DECIMAL, dbType)
                    .comment("员工薪资"),
                new ColumnDefinition("DEPT_ID", ColumnType.INTEGER, dbType)
                    .notNull()
                    .comment("部门编号"),
                new ColumnDefinition("CREATE_TIME", ColumnType.TIMESTAMP, dbType)
                    .notNull()
                    .comment("创建时间")
            );
            
            // 创建表时添加表注释
            util.createTable(
                "EMPLOYEE",           // 表名
                "员工信息表",         // 表注释
                columns              // 列定义
            );
            
            System.out.println("表创建成功！");
            util.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
