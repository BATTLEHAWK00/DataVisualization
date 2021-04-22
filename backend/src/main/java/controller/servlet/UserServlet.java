package controller.servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    //获取用户操作
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    //更新用户数据
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

}
