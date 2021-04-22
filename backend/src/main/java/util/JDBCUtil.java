package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * 封装数据库连接
 */
public class JDBCUtil {
    private static final JDBCUtil instance = new JDBCUtil();
    DataSource dataSource;

    //设计模式中的单例模式
    public static JDBCUtil getInstance() {
        return instance;
    }

    //数据库连接池初始化,使用阿里巴巴Druid连接池
    private JDBCUtil() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(prop);
            System.out.println("数据库连接池初始化...");
        } catch (Exception e) {
            System.err.println("数据库连接池初始化异常!!!");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接并自动关闭连接
     *
     * @param callback 回调Lambda函数，在Lambda函数中编写逻辑
     */
    public void getConnection(Consumer<Connection> callback) {
        try {
            Connection conn = dataSource.getConnection();
            callback.accept(conn);
            conn.close();
        } catch (SQLException throwables) {
            System.err.println("获取数据库连接失败！！！");
            throwables.printStackTrace();
        }
    }

}
