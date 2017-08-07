package com.bjqx.baseData.intercepter;

import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.base.common.ThreadLocalWrapper;
import com.bjqx.base.common.UserContext;
import com.bjqx.base.utils.CookieUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 *
 * @author 阿坤625431565@qq.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //String requestUrl = request.getServletPath();
        String requestUrl = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + contextPath;

        //cas服务器地址(通过shirio框架区分内外网动态配置)
//		request.setAttribute("casServerPath", GlobalConfigure.CAS_SERVER_ADDR);
        request.setAttribute("basePath", basePath);
        request.setAttribute("fileServerPath", GlobalConfigure.HTTP_FILE_SERVER_PATH);
        request.setAttribute("localPath", GlobalConfigure.FILE_SERVER_LOCAL_PATH);
        Object applicationContext = ThreadLocalWrapper.get(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY);
        if (applicationContext == null) {
            ServletContext servletContext = request.getSession().getServletContext();
            ThreadLocalWrapper.put(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY, WebApplicationContextUtils.getWebApplicationContext(servletContext));
        }

        Boolean noInterceptor = (Boolean) request.getAttribute("noInterceptor");
        if (noInterceptor != null && noInterceptor.booleanValue()) {
            return true;
        }


        Subject subject = SecurityUtils.getSubject();
        logger.debug("subject.isAuthenticated()={}", subject.isAuthenticated());
        if (!subject.isAuthenticated()) {
            for (String url : GlobalConfigure.NO_INTERCEPTOR) {
                if (pathMatcher.match(url, requestUrl)) {
                    request.setAttribute("noInterceptor", true);
                    return true;
                }
            }
            //不记录请求的地址，cas登陆成功之后默认返回shiro-contextCas.xml中配置的home.html地址
            //WebUtils.saveRequest(request);
            logger.debug("url not match {}", requestUrl);
            response.sendRedirect(contextPath + "/login.html");
            return false;
        }

        HttpSession session = request.getSession();
        UserContext userContext = (UserContext) session.getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
        session.setAttribute("contextPath", contextPath);
        if (userContext == null) {
            logger.debug("userContext == null response.sendRedirect(contextPath+\"/login.html\")");
            response.sendRedirect(contextPath + "/login.html");
            return false;
        }

        userContext.setAddressIp(CookieUtils.getCurrIpAddress(request));
        ThreadLocalWrapper.bind(userContext);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalWrapper.unbind();
        super.afterCompletion(request, response, handler, ex);
    }
}
