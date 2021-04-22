package service;

import bean.user.UserRegBean;

import javax.servlet.http.HttpSession;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user 待注册的JavaBean
     */
    void doRegisterUser(UserRegBean user);

    /**
     * 处理用户登录逻辑
     *
     * @param keyword 登录关键字(用户名,邮箱,手机号)
     * @param passwd  密码
     * @return 返回UserID
     */
    boolean doUserLogin(String keyword, String passwd, HttpSession session);
}
