package com.bjqx.base.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 跨站脚本攻击
 *
 * @Author 阿坤625431565@qq.com
 * @Data 2017/8/1 12:06
 */
public class XSSFilter implements Filter {

    FilterConfig filterConfig = null;

    private static final Logger logger = LoggerFactory.getLogger(XSSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) request), response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
