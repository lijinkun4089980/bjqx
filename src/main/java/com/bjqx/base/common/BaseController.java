package com.bjqx.base.common;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseController {


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
	    binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor());
	}

	public final static String SUCCESS = "success";
	public final static String ERROR = "error";
	public final static String FALSE = "false";
	public final static String EXCEPTION_MSG = "系统内部异常，请联系管理员";

	public Map<String, String> getRequsetMap(HttpServletRequest request) {
		Map<String, String> bm = new HashMap<String, String>();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				bm.put(key, values[0].trim());
			}
		}
		return bm;
	}

}


