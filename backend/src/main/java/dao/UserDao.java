package dao;

import bean.user.UserRegBean;

public interface UserDao {
    /**
     * 注册用户
     */
    void doRegisterUser(UserRegBean userRegBean);

    String doUserlogin(String keyword, String passwd);
}
