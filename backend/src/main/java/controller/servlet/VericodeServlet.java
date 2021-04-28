package controller.servlet;

import bean.responses.Response;
import com.alibaba.fastjson.JSON;
import util.ProjectUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/vericode")
public class VericodeServlet extends HttpServlet {
    public static final int CODE_LENGTH = 6;

    //获取验证码接口
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应体类型
        resp.setContentType("image/png");
        //获取验证码并写入响应体
        String code = ProjectUtil.getInstance().getVerificationCode(75, CODE_LENGTH, resp.getOutputStream());
        //将验证码设置到session中
        req.getSession().setAttribute("vericode", code);
    }

    //提交验证码接口
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session会话
        HttpSession session = req.getSession();
        //拿到请求验证码
        String reqCode = req.getParameter("code").trim();
        //初始化Json
        Response response = new Response();
        //获取正确验证码
        String vericode = (String) session.getAttribute("vericode");
        //比对验证码
        if (!reqCode.equalsIgnoreCase((String) session.getAttribute("vericode"))) {
            response.setCode(1);
            response.setMsg("验证码错误!");
            //验证码错误，将Session中的验证码清空
            session.setAttribute("vericode", null);
        }
        resp.getWriter().print(JSON.toJSONString(response));
    }
}
