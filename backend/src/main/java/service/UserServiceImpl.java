package service;

import bean.user.UserRegBean;
import dao.UserDao;
import dao.UserDaoImpl;
import util.SecurityUtil;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void doRegisterUser(UserRegBean user) {
        user.setPasswd(SecurityUtil.getInstance().getSaltyMD5(user.getPasswd(), user.getUsername()));
        userDao.doRegisterUser(user);
    }

    @Override
    public boolean doUserLogin(String keyword, String passwd, HttpSession session) {
        passwd = SecurityUtil.getInstance().getSaltyMD5(passwd, keyword);
        String userID = userDao.getUserIDByLogin(keyword, passwd);
        if (userID != null) {
            session.setAttribute("loggedUser", userID);
            return true;
        } else {
            return false;
        }
    }
}
