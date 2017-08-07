package com.bjqx.base.common;

import com.bjqx.base.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;


@Component
public class GlobalConfigure {

    public static Properties application = null;

    /**
     * 本地项目文件存放路径
     */
    public static String FILE_SERVER_LOCAL_PATH = null;
    /**
     * 服务器上文件存放路径
     */
    public static String FILE_SERVER_ZTMIS_PATH = null;
    /**
     * 文件存放路径
     */
    public static String HTTP_FILE_SERVER_PATH = null;
    public static String HTTP_ZTMIS_FILE_SERVER_PATH = null;
    //public static String CAS_SERVER_PATH = null;

    public static String YUN_JPUSH_API_KEY = null;
    public static String YUN_JPUSH_MASTER_SECRET = null;
    public static String ZTWD_PATH = null;
    public static String SYNCHROLOCK = null;

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_HOME_PAGE_SIZE = 10;
    public static final int DEFAULT_HOME_NOTICE_PAGE_SIZE = 5;
    public static final int MAX_PAGE_SIZE = 100;


    public static final String IMAGE_REGEX_TYPE = "image/[\\w|-]+";

    public static final String VIDEO_REGEX_TYPE = "video/[\\w|-]+";

    public static WebApplicationContext getWebApplicationContext() {
        return (WebApplicationContext) ThreadLocalWrapper.get(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY);
    }

    public static final String SPRING_APPLICATION_CONTEXT_KEY = GlobalConfigure.class.getName() + "_SPRING_APPLICATION_CONTEXT_KEY";
    public static final String _USER_URI_DEFINITION_KEY = "_USER_URI_DEFINITION_KEY";
    public static final String URI_DEFINITION_KEY = "_URI_DEFINITION_KEY";
    public static final String[] NO_INTERCEPTOR = {"/static/**", "/login.html", "/dologin", "/home.html", "/home/index", "/dologout", "/rest/**"};

//	@Autowired
//	private Environment environment;

//	private static final String SPRING_ACTIVE_PROFILE_DEVELOPMENT = "development";
//	private static final String SPRING_ACTIVE_PROFILE_PRODUCTION = "production";

    @PostConstruct
    private void init() {
//		String profile = environment.getProperty(StandardServletEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,  "development");
//		Properties application = null;
//		if (SPRING_ACTIVE_PROFILE_DEVELOPMENT.equals(profile)) {
//			application = PropertiesUtils.getProperties("application.properties");
//		} else if (SPRING_ACTIVE_PROFILE_PRODUCTION.equals(profile)) {
//			application = PropertiesUtils.getProperties("application.properties");
//		}

        application = PropertiesUtils.getProperties("application.properties");
        String os = System.getProperties().getProperty("os.name");
        HTTP_FILE_SERVER_PATH = application.getProperty("http.file.server");
        HTTP_ZTMIS_FILE_SERVER_PATH = application.getProperty("http.ztmis.file.serve");
        //CAS_SERVER_PATH = application.getProperty("cas.server.ip.address");
        //ZTWD_PATH = application.getProperty("ztwd.server.ip.address");
        if (StringUtils.startsWithIgnoreCase(os, "win")) {
            FILE_SERVER_LOCAL_PATH = application.getProperty("windows.file.server.local.path");
            FILE_SERVER_ZTMIS_PATH = application.getProperty("windows.file.server.ztmis.path");
        } else {
            FILE_SERVER_LOCAL_PATH = application.getProperty("linux.file.server.local.path");
            FILE_SERVER_ZTMIS_PATH = application.getProperty("linux.file.server.ztmis.path");
        }
        YUN_JPUSH_API_KEY = application.getProperty("yun.jpush.api.key");
        YUN_JPUSH_MASTER_SECRET = application.getProperty("yun.jpush.master.secret");
        SYNCHROLOCK = application.getProperty("synchro_lock");
    }

    @PreDestroy
    private void destroy() {
        GlobalConfigure.application = null;
        GlobalConfigure.FILE_SERVER_LOCAL_PATH = null;
        GlobalConfigure.HTTP_FILE_SERVER_PATH = null;
        //GlobalConfigure.CAS_SERVER_PATH = null;
        GlobalConfigure.YUN_JPUSH_API_KEY = null;
        GlobalConfigure.YUN_JPUSH_MASTER_SECRET = null;
    }


    public static final String MISC_SUFFIX = "_@MISC@";
    public static final String DATA_ACCESS_CNNTSECTION = GlobalConfigure.class.getName() + "_DATA_ACCESS_CNNTSECTION_";
    public static final String DATA_ACCESS_TRANSTATION = GlobalConfigure.class.getName() + "_DATA_ACCESS_TRANSTATION_";
    public static final String DATA_ACCESS_PAGINATION = GlobalConfigure.class.getName() + "_DATA_ACCESS_PAGINATION_";
    public static final String DATA_ACCESS_DEPARTMENT = GlobalConfigure.class.getName() + "_DATA_ACCESS_DEPARTMENT_";
    public static final String DATA_ACCESS_PAPERMISSION = GlobalConfigure.class.getName() + "_DATA_ACCESS_PAPERMISSION_";
    public static final String DATA_ACCESS_PAPERMISSION_TYPE = GlobalConfigure.class.getName() + "_DATA_ACCESS_PAPERMISSION_TYPE_";
    public static final String DATA_ACCESS_PAPERMISSION_DEPARTMENT = GlobalConfigure.class.getName() + "_DATA_ACCESS_PAPERMISSION_DEPARTMENT_";
    public static final String DATA_ACCESS_PAPERMISSION_TYPE_RAILLINE_BLOCK = GlobalConfigure.class.getName() + "_DATA_ACCESS_PAPERMISSION_TYPE_RAILLINE_BLOCK_";


    public static final String PROJECTS = "projects";
    public static final String PROJECTCODES = "projectCodes";
    public static final String DEPARTMENT_LIST = "departmentList";

    public static final String RAIL_LIST_ALL = "railListAll";
    public static final String POLE_ID_LIST = "poleIdList";

    public static final String DEPARTMENT = "department";
    //TYPE_SECTION是当前人所属段，如果是段级以上的部门，当前字段就为空
    public static final String TYPE_SECTION = "typeSection";
    public static final String EMPLOYEE = "employee";
    public static final String LOGIN_IP = "loginIp";
}
