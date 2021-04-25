package controller.servlet;

import bean.responses.Response;
import com.alibaba.fastjson.JSON;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //测试接口用,使用get方法登录不安全
        doPost(req, resp);
    }

    //登录接口
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从请求中获取用户名
        String username = req.getParameter("username");
        //从请求中获取密码
        String passwd = req.getParameter("passwd");
        //执行登录操作
        Response res = userService.doUserLogin(username, passwd, req.getSession());
        //将json写到响应体中
        resp.getWriter().print(JSON.toJSONString(res));
    }

    //注销接口
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //执行注销操作
        Response res = userService.doUserLogout(req.getSession());
        //将json写到响应体中
        resp.getWriter().print(JSON.toJSONString(res));
    }
}
