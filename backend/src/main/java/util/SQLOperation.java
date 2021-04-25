package util;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 封装数据库的增删改查操作
 */
public class SQLOperation {
    private String sql;
    private Connection conn;
    private PreparedStatement statement;

    /**
     * @param conn 数据库连接
     * @param sql  SQL语句
     */
    public SQLOperation(Connection conn, String sql) {
        this.conn = conn;
        this.sql = sql;
        getStatement();
    }

    /**
     * @param conn 数据库连接
     */
    public SQLOperation(Connection conn) {
        this.conn = conn;
    }

    /**
     * 设置SQL语句
     *
     * @param sql 需要执行的SQL语句
     */
    public void setSql(String sql) {
        this.sql = sql;
        getStatement();
    }

    private void getStatement() {
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 将参数装进SQL语句中
     *
     * @param args 需要填入SQL语句中的参数
     */
    public void prepareArgs(Object... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 执行查询语句
     *
     * @return 返回包含字典的查询列表
     */
    public List<Map<String, Object>> executeQuery() throws SQLException {
        List<Map<String, Object>> mapList = new LinkedList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> map = new LinkedHashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                mapList.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return mapList;
    }

    /**
     * 执行更新语句
     *
     * @return 返回影响行数
     */
    public int excuteUpdate() throws SQLException {
        int cnt = -1;
        try {
            cnt = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return cnt;
    }
}
