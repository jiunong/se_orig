package cloud.database;

public class OracleDatabaseListener {
//
//    public static void main(String[] args) throws Exception {
//        // 连接到Oracle数据库
//        String url = "jdbc:oracle:thin:@localhost:1521:xe";
//        String username = "your_username";
//        String password = "your_password";
//        Connection connection = DriverManager.getConnection(url, username, password);
//
//        // 创建DatabaseChangeRegistration对象
//        OracleConnection oracleConnection = (OracleConnection) connection;
//        DatabaseChangeRegistration dcr = oracleConnection.registerDatabaseChangeNotification();
//
//        // 创建DatabaseChangeListener对象
//        DatabaseChangeListener listener = new DatabaseChangeListener() {
//            @Override
//            public void onDatabaseChangeNotification(DatabaseChangeEvent event) {
//                // 处理数据库变更事件
//                System.out.println("Table Name: " + event.getTableChangeDescription()[0].getTableName());
//                System.out.println("Operation: " + event.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowOperation());
//            }
//        };
//
//        // 注册监听器
//        dcr.addListener(listener);
//
//        // 执行SQL语句以启用监听
//        try (Statement statement = connection.createStatement()) {
//            statement.setDatabaseChangeRegistration(dcr);
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");
//            while (resultSet.next()) {
//                // 处理查询结果
//                System.out.println("ID: " + resultSet.getInt("id"));
//                System.out.println("Name: " + resultSet.getString("name"));
//            }
//        }
//
//        // 阻止主线程退出
//        Thread.sleep(Integer.MAX_VALUE);
//
//        // 关闭连接
//        connection.close();
//    }
}
