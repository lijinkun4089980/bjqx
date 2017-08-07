package com.bjqx.base.utils;

import com.bjqx.base.common.GlobalConfigure;
import com.bjqx.base.common.ThreadLocalWrapper;
import com.bjqx.base.common.UserContext;
import com.bjqx.baseData.entity.User;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目工具类
 */
public class BjqxUtils {

    public static ApplicationContext getApplicationContext() {
        return (ApplicationContext) ThreadLocalWrapper.get(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY);
    }

    /**
     * 获取登陆用户名
     *
     * @return
     */
    public static String getUserName() {
        if (getUserContext() != null) {
            User user = getUserContext();
            return user.getUserName();
        }
        return "";
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Integer getUserId() {
        if (getUserContext() != null) {
            User user = getUserContext();
            return user.getId();
        }
        return null;
    }


    /**
     * 获取登录名
     *
     * @return
     */
    public static String getLoginName() {
        if (getUserContext() != null) {
            User user = getUserContext();
            return user.getLoginName();
        }
        return "";
    }

    /**
     * 获取当前用户
     *
     * @return {@code Employee}
     */
    public static User getUserContext() {
        UserContext userContext = ThreadLocalWrapper.getUserContext();
        if (userContext == null || userContext.getCurrentUser() == null) {
            throw new RuntimeException("当前用户未登录！");
        }
        return userContext.getCurrentUser();
    }

    public static boolean isValid(String link) {
        URL url;
        try {
            url = new URL(link);
            HttpURLConnection connt = (HttpURLConnection) url.openConnection();
            connt.setRequestMethod("HEAD");
            String strMessage = connt.getResponseMessage();
            if (strMessage.compareTo("Not Found") == 0) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
