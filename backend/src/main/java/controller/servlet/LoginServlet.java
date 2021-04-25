package controller.servlet;

import bean.responses.Response;
import com.alibaba.fastjson.JSON;
import service.UserService;
import service.UserServiceImpl;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        Response res = new Response();
        if (userService.doUserLogin(username, passwd, req.getSession())) {
            res.setMsg("OK");
        } else {
            res.setCode(101);
            res.setMsg("用户名或密码错误!");
        }
        resp.getWriter().print(JSON.toJSONString(res));
    }
}
