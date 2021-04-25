package controller.servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

}
