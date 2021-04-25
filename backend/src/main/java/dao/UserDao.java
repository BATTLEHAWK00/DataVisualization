package dao;

import bean.exception.ServiceException;
import bean.user.UserRegBean;

public interface UserDao {
    /**
     * 注册用户
     *
     * @param userRegBean 用户注册Bean对象
     */
    void doRegisterUser(UserRegBean userRegBean) throws ServiceException;

    /**
     * 获取用户ID(根据用户登录关键信息)
     *
     * @param keyword 关键字(用户名,邮箱,手机号)
     * @param passwd  密码(密文)
     * @return 返回用户ID(失败返回空)
     */
    String getUserIDByLogin(String keyword, String passwd);

    /**
     * 根据关键字获取用户ID
     *
     * @param keyword 关键字(用户名,邮箱,手机号)
     * @return 返回用户ID(失败返回空)
     */
    String getUserIDByKeyword(String keyword);
}
