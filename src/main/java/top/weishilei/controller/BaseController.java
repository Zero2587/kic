package top.weishilei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.weishilei.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author weishilei
 * 控制器基类
 */
@Controller
public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return session;
    }

    /**
     * 获取classpath路径
     * @return
     */
    public String getClassPath() {
        return this.getClass().getResource("/").getPath();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public User getUser() {
        return (User) session.getAttribute("loginUser");
    }

}
