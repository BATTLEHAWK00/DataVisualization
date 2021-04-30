package service;

import bean.exception.DAOException;
import bean.exception.ServiceException;
import bean.responses.Response;
import bean.user.UserRegBean;
import dao.UserDao;
import dao.UserDaoImpl;
import util.SecurityUtil;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public Response doRegisterUser(UserRegBean user) {
        //初始化响应体
        Response response = new Response();
        try {
            //检测用户是否已注册
            if (userDao.getUserIDByKeyword(user.getUsername()) != null) {
                response.setCode(3);
                response.setMsg("用户已被注册!");
                return response;
            }
            //将密码使用md5加密
            user.setPasswd(SecurityUtil.getInstance().getSaltyMD5(user.getPasswd(), user.getUsername()));
            //执行注册操作
            userDao.doRegisterUser(user);
        } catch (DAOException e) {
            //异常处理
            response.setCode(2);
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @Override
    public Response doUserLogin(String keyword, String passwd, HttpSession session) {
        Response response = new Response();
        //处理已登录Session，避免重复登录
        if (session.getAttribute("loggedUser")!=null){
            response.setCode(101);
            response.setMsg("你已经登录过了!");
            return response;
        }
        //加密密码
        passwd = SecurityUtil.getInstance().getSaltyMD5(passwd, keyword);
        //获取用户ID
        String userID = null;
        try {
            userID = userDao.getUserIDByLogin(keyword, passwd);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //判断是否登录成功
        if (userID != null) {
            //成功，则将用户添加到Session会话
            session.setAttribute("loggedUser", userID);
        } else {
            //失败，处理响应信息
            response.setCode(102);
            response.setMsg("用户名或密码错误!");
        }
        return response;
    }

    @Override
    public Response doUserLogout(HttpSession session) {
        Response response = new Response();
        //若Session中已存在用户信息，则删除
        if (session.getAttribute("loggedUser") != null) {
            session.setAttribute("loggedUser", null);
        }
        return response;
    }

}
