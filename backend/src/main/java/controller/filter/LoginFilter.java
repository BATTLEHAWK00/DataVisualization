package controller.filter;

import bean.responses.Response;
import com.alibaba.fastjson.JSON;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录权限验证过滤
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter {
    //Filter白名单
    public static final Set<String> filterIgnorePaths = new HashSet<>() {
        {
            add("/user/login");
            add("/user/register");
            add("/vericode");
        }
    };

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setCharacterEncoding("utf-8");
        res.setContentType("application/json;charset=utf-8");
        //Filter白名单处理
        if (filterIgnorePaths.contains(req.getServletPath())) {
            chain.doFilter(req, res);
            return;
        }
        HttpSession session = req.getSession();
        String loggedUID = (String) session.getAttribute("loggedUser");
        if (loggedUID == null) {
            Response response = new Response();
            response.setMsg("你必须登录!");
            response.setCode(1);
            res.getWriter().println(JSON.toJSONString(response));
        } else {
            chain.doFilter(req, res);
        }
    }
}
