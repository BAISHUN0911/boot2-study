package com.baishun.service1.controller;

import com.baishun.service1.config.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/24 15:06
 */
@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {
    @Autowired
    private SessionManager sessionManager;

    @GetMapping("/setSession")
    public String setSession(HttpServletRequest request) {
        request.getSession().setAttribute("test", "Hello Spring Session");
        return "Session set successfully";
    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        log.info("请求的SessionId: {}", request.getRequestedSessionId());
        log.info("请求的User-Agent: {}", request.getHeader("User-Agent"));
        String value = (String) request.getSession().getAttribute("test");
        // 非自定义添加的Attribute无法通过这种方式获取
//        String maxInactiveInterval = (String) request.getSession().getAttribute("maxInactiveInterval");
        if (sessionManager.getSessionExpirationTime(request) == -1) {
            return "session expire, please login again";
        }
        return value;
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "jack");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return "cookie set success";
    }

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies).map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }
        return "Cookie is empty";
    }
}
