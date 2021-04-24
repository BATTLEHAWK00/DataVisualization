package controller;

import util.JDBCUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 用于初始化的Servlet
 */
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        JDBCUtil.getInstance();
    }
}
