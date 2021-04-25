package dao;

import bean.exception.ServiceException;
import bean.user.UserRegBean;
import util.JDBCUtil;
import util.SQLOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UserDaoImpl implements UserDao {
    @Override
    public void doRegisterUser(UserRegBean userRegBean) throws ServiceException {
        AtomicReference<ServiceException> exception = new AtomicReference<>();
        //获取数据库连接
        JDBCUtil.getInstance().getConnection(connection -> {
            //实例化SQL操作类
            SQLOperation operation = new SQLOperation(connection);
            //设置SQL语句
            operation.setSql("INSERT INTO t_user (username,passwd,usertype,regtime) VALUES(?,?,?,now())");
            //将变量填入上述SQL语句中的问号占位符
            operation.prepareArgs(
                    userRegBean.getUsername(),
                    userRegBean.getPasswd(),
                    userRegBean.getUserType()
            );
            //执行更新操作
            try {
                operation.excuteUpdate();
            } catch (SQLException throwables) {
                exception.set(new ServiceException("服务器错误"));
            }
        });
        //处理注册不成功的情况
        if (exception.get() != null) {
            throw exception.get();
        }
    }

    @Override
    public String getUserIDByLogin(String keyword, String passwd) {
        AtomicReference<String> uid = new AtomicReference<>();
        JDBCUtil.getInstance().getConnection(connection -> {
            SQLOperation operation = new SQLOperation(connection);
            operation.setSql("SELECT userid FROM v_login where keyword=? and passwd=?");
            operation.prepareArgs(
                    keyword,
                    passwd
            );
            try {
                List<Map<String, Object>> list = operation.executeQuery();
                if (list.size() == 1)
                    uid.set(list.get(0).toString());
            } catch (SQLException ignored) {

            }
        });
        return uid.get();
    }

    @Override
    public String getUserIDByKeyword(String keyword) {
        AtomicReference<String> uid = new AtomicReference<>();
        JDBCUtil.getInstance().getConnection(connection -> {
            SQLOperation operation = new SQLOperation(connection);
            operation.setSql("SELECT userid FROM v_login where keyword=?");
            operation.prepareArgs(keyword);
            try {
                List<Map<String, Object>> list = operation.executeQuery();
                if (list.size() == 1)
                    uid.set(list.get(0).toString());
            } catch (SQLException ignored) {

            }
        });
        return uid.get();
    }
}
