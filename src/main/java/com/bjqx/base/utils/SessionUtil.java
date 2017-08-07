package com.bjqx.base.utils;


import com.bjqx.base.common.ThreadLocalWrapper;
import com.bjqx.base.common.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 从Session中获取属性的工具类
 * 使用此工具类，因为我们使用的shiro安全框架，可以使用SecurityUtils与web容器解耦。
 *
 * @author 阿坤
 * 0929:修改session信息的获取方式，从userContext获取需要的信息：
 * 修改的方法：getCurrentUser|getDepartmentId
 */
public final class SessionUtil {
    private final static Logger logger = LoggerFactory.getLogger(SessionUtil.class);

    private SessionUtil() {
    }

    /**
     * 获取Session对象
     */
    public static Session getSession() {
        try {
            return SecurityUtils.getSubject().getSession();
        } catch (Exception e) {
            logger.error("getSession:获取session异常" + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 放置属性进入session
     *
     * @param key
     * @param value
     */
    public static void setAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从session中获取属性
     *
     * @param key
     * @return
     */
    public static Object getAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 从session中获取：userContext
     */
    public static UserContext getUserContext() {
        return (UserContext) getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
    }
}
