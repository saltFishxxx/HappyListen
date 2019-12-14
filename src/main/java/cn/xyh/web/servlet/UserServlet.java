package cn.xyh.web.servlet;

import cn.xyh.model.User;
import cn.xyh.service.UserService;
import cn.xyh.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();

    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        user = pacakageObject(user, request);
        User sqlUser = userService.login(user);
        if (sqlUser != null) {
            request.getSession().setAttribute("user", sqlUser);
            response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        }else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String verifyCode = request.getParameter("verifyCode");
        String sessionCode = (String) request.getSession().getAttribute("checkcode_session");
        request.getSession().removeAttribute("checkcode_session");
        //判断验证码
        if (!sessionCode.equals(verifyCode)){
            request.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user = pacakageObject(user, request);
        user.setRole("普通用户");
        user.setActiveCode(UUIDUtil.getUUid());
        if (userService.register(user)) {
            response.sendRedirect(request.getContextPath()+"/jsp/registersuccess.jsp");
        }else {
            request.setAttribute("error", "注册失败");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }

    //退出
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
    }

    //激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        String activeCode = request.getParameter("activeCode");
        if (activeCode != null && !"".equals(activeCode)) {
            if (userService.ifActive(activeCode)) {
                if (userService.active(activeCode)) {
                    response.getWriter().write("<h2>激活成功, <a href='http://localhost/bookstore/jsp/login.jsp'>请登录</a></h2>");
                }else {
                    response.getWriter().write("<h2>激活失败</h2>");
                }
            }else {
                response.getWriter().write("<h2>已激活</h2>");
            }
        }else {
            response.getWriter().write("<h2>激活码不能为空</h2>");
        }
    }

    //判断是否处于登陆状态
    public void ifLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath()+"/jsp/myAccount.jsp");
        }else {
            response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
        }
    }

    //更新用户信息
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        user = pacakageObject(user, request);
        if(userService.updateUser(user)) {
            response.sendRedirect(request.getContextPath()+"/jsp/modifyUserInfoSuccess.jsp");
        }else {
            request.setAttribute("error", "更新失败");
            request.getRequestDispatcher("/jsp/modifyuserinfo.jsp").forward(request, response);
        }
    }

    //根据id查询用户
    public void findById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        User sqlUser = userService.findUserById(id);
        request.setAttribute("user", sqlUser);
        request.getRequestDispatcher("/jsp/modifyuserinfo.jsp").forward(request, response);
    }
}
