package dao;

import bean.exception.DAOException;
import bean.exception.ServiceException;
import bean.user.UserBean;
import bean.user.UserRegBean;
import util.JDBCUtil;
import util.SQLOperation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class UserDaoImpl implements UserDao {
    @Override
    public void doRegisterUser(UserRegBean userRegBean) throws DAOException {
        AtomicReference<DAOException> exception = new AtomicReference<>();
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
                exception.set(new DAOException("服务器错误"));
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
                    uid.set(list.get(0).get("userid").toString());
            } catch (SQLException ignored) {

            }
        });
        return uid.get();
    }

    @Override
    public UserBean getUserByUID(String uid) throws DAOException {
        AtomicReference<UserBean> userBean = new AtomicReference<>();
        AtomicReference<DAOException> daoException = new AtomicReference<>();
        JDBCUtil.getInstance().getConnection(connection -> {
            SQLOperation operation = new SQLOperation(connection);
            operation.setSql("SELECT * FROM t_user where userid=?");
            operation.prepareArgs(Integer.parseInt(uid));
            try {
                List<Map<String, Object>> list = operation.executeQuery();
                if (list.size() == 1) {
                    Map<String, Object> mp = list.get(0);
                    userBean.set(new UserBean());
                    userBean.get().setUserid(mp.get("userid").toString());
                    userBean.get().setUsername(mp.get("username").toString());
                    userBean.get().setPasswd(mp.get("passwd").toString());
                    userBean.get().setNickname(mp.get("nickname").toString());
                    userBean.get().setRegtime(Long.toString(((LocalDateTime) mp.get("regtime")).toEpochSecond(ZoneOffset.UTC)));
                    userBean.get().setUsertype((int) mp.get("usertype"));
                    userBean.get().setEmail(mp.get("email").toString());
                    userBean.get().setPhone(mp.get("phone").toString());
                    userBean.get().setLocked((boolean) mp.get("islocked"));
                }
            } catch (SQLException exception) {
                daoException.set(new DAOException("服务器错误"));
            }
        });
        if (daoException.get() != null)
            throw daoException.get();
        return userBean.get();
    }
}
