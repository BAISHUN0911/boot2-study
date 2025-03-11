package com.baishun.service1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/24 17:10
 */
@Component
public class SessionManager {
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @Autowired
    public SessionManager(FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * 获取session过期时间
     * @param request 请求
     * @return session过期的秒数
     */
    public long getSessionExpirationTime(HttpServletRequest request) {
        String sessionId = request.getRequestedSessionId();
        Session session = sessionRepository.findById(sessionId);
        if (session != null) {
            return session.getMaxInactiveInterval().getSeconds();
        }
        return -1;
    }
}
