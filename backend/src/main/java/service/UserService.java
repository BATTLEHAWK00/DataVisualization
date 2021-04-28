package service;

import bean.responses.Response;
import bean.user.UserRegBean;

import javax.servlet.http.HttpSession;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user 待注册的JavaBean
     * @return 返回响应体
     */
    Response doRegisterUser(UserRegBean user);

    /**
     * 处理用户登录逻辑
     *
     * @param keyword 登录关键字(用户名,邮箱,手机号)
     * @param passwd  密码
     * @param session 待存储的Session会话
     * @return 返回响应体
     */
    Response doUserLogin(String keyword, String passwd, HttpSession session);

    /**
     * 处理用户注销
     *
     * @param session 待存储的Session会话
     * @return 返回响应体
     */
    Response doUserLogout(HttpSession session);
}
