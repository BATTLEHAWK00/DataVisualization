package dao;

import bean.user.UserRegBean;
import util.JDBCUtil;
import util.SQLOperation;

public class UserDaoImpl implements UserDao {
    @Override
    public void doRegisterUser(UserRegBean userRegBean) {
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
            operation.excuteUpdate();
        });
    }

    @Override
    public String doUserlogin(String keyword, String passwd) {
        return null;
    }
}
