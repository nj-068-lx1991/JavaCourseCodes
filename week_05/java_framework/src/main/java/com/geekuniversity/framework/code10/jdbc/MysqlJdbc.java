package com.geekuniversity.framework.code10.jdbc;


import java.sql.*;
import java.util.*;

/**
 * 使用 JDBC 原生接口，实现数据库的增删改查操作。
 * 使用事务，PrepareStatement 方式，批处理方式，改进上述操作
 *
 * @author Created by lx_068
 */
public class MysqlJdbc {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql jdbc driver");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "admin", "123456");
            if (connection != null) {
                System.out.println("Connection success!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    private void insert(String table, String column, List<Object> values) {
        try {
            String insertTemplate = buildInsertSqlTemplate(table, column, values.size());
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 1; i < values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i - 1));
            }
            System.out.println(preparedStatement.toString());

            preparedStatement.execute();
            System.out.println("Insert successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildInsertSqlTemplate(String table, String column, int valueAmount) {
        // 使用regex将字符串重复N次。
        String repeated = new String(new char[Math.max(0, valueAmount - 1)]).replace("\0", ",?");
        return "insert into " + table + " " + column + " values (?" + repeated + ")";
    }

    private List<Map<String, Object>> query(String table, Map<String, Object> values) {
        try {
            String sqlTemplate = buildQuerySqlTemplate(table, values, null);

            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                for (String key : values.keySet()) {
                    values.put(key, resultSet.getObject(key));
                }
                list.add(new HashMap<>(values));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String buildQuerySqlTemplate(String table, Map<String, Object> values, String condition) {
        String sqlTemplate = "select " + values.keySet().toString().substring(1,
                values.keySet().toString().length() - 1) + " from " + table;
        if (condition != null) {
            sqlTemplate += " where " + condition;
        }
        return sqlTemplate;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }

    public static void main(String[] args) {
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("id", 0);
        valuesMap.put("name", "name");
        valuesMap.put("password", "password");
        valuesMap.put("phoneNumber", "phoneNumber");
        valuesMap.put("money", 0);
        System.out.println(valuesMap.keySet().toString().substring(1, valuesMap.keySet().toString().length() - 1));

        try {
            MysqlJdbc mysqlJdbc = new MysqlJdbc();
            mysqlJdbc.createConnection();

            String table = "users";
            String columns = "(name, password, phoneNumber, money)";
            List<Object> values = Arrays.asList("name", "password", "phoneNumber", 0);

            mysqlJdbc.insert(table, columns, values);

            List<Map<String, Object>> results = mysqlJdbc.query(table, valuesMap);
            assert results != null;
            for (Map<String, Object> r : results) {
                System.out.println(r.toString());
            }
            mysqlJdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
