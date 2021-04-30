package controller.servlet;

import bean.responses.Response;
import bean.user.UserRegBean;
import com.alibaba.fastjson.JSON;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //测试接口用,使用get方法登录不安全
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserRegBean regBean = new UserRegBean();
        regBean.setUsername(req.getParameter("username"));
        regBean.setPasswd(req.getParameter("passwd"));
        regBean.setEmail(req.getParameter("email"));
        regBean.setPhone(req.getParameter("phone"));
        Response response = userService.doRegisterUser(regBean);
        resp.getWriter().print(JSON.toJSONString(response));
    }
}
