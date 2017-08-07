package com.bjqx.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public final class PropertiesUtils {
	
	private static Logger logger = LogManager.getLogger(PropertiesUtils.class);
	
	public static final String FILE_NAME = "config/application.properties";
	
	private PropertiesUtils(){}
	
	public static Properties getProperties(String... fileNames) {
		Properties prop = new Properties();
		for (String fileName : fileNames) {
			InputStream is = null;
			try {
				is = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
				prop.load(is);
			} catch (IOException e) {
				logger.error("配置文件装载异常",e);
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return prop;
	}
	
	public static Properties getProperties(){
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(PropertiesUtils.FILE_NAME);
			prop.load(is);
		} catch (IOException e) {
			logger.error("配置文件装载异常",e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		return prop;
	}
	
	public static String getProperty(String key){
		return PropertiesUtils.getProperties().getProperty(key);
	}
}
