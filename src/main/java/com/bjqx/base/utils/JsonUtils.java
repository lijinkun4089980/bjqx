package com.bjqx.base.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static void main(String[] args) throws Exception {
        //List<Employee> list = new ArrayList<>();
        //Employee emp = new Employee();
        //emp.setAddress("地址");
        //emp.setCreaterTime(new Date());
        //emp.setId(1);
        //list.add(emp);
        //emp = new Employee();
        //emp.setAddress("地址2");
        //emp.setCreaterTime(new Date());
        //emp.setId(12);
        //list.add(emp);
        //String jsonString = JsonUtils.objToString(list);
        //List<Map> mapList = JsonUtils.readJson(jsonString, List.class, Map.class);
        //Employee demo = new Employee();
        //List<Employee> demoList = map2Java(demo, mapList);
        //System.out.println(demoList.size());
    }


    /**
     * Map集合对象转化成 JavaBean集合对象
     *
     * @param javaBean JavaBean实例对象
     * @param mapList  Map数据集对象
     * @return
     * @throws ParseException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings({"rawtypes"})
    public static <T> List<T> map2Java(T javaBean, List<Map> mapList) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, ParseException {
        if (mapList == null || mapList.isEmpty()) {
            return null;
        }
        List<T> objectList = new ArrayList<T>();
        T object;
        for (Map map : mapList) {
            if (map != null) {
                object = map2Java(javaBean, map);
                objectList.add(object);
            }
        }
        return objectList;
    }

    /**
     * Map对象转化成 JavaBean对象
     *
     * @param javaBean JavaBean实例对象
     * @param map      Map对象
     * @return
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws ParseException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> T map2Java(T javaBean, Map map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, ParseException {
        // 获取javaBean属性
        BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
        // 创建 JavaBean 对象
        Object obj = javaBean.getClass().newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            String propertyName; // javaBean属性名
            Object propertyValue; // javaBean属性值
            for (PropertyDescriptor pd : propertyDescriptors) {
                propertyName = pd.getName();
                if (!"class".equals(propertyName) && map.containsKey(propertyName)) {
                    Method method = pd.getWriteMethod();
                    Class[] parTypes = method.getParameterTypes();
                    propertyValue = map.get(propertyName);
                    if (null != propertyValue) {
                        if (parTypes[0].equals(Date.class)) {
                            if (propertyValue.toString().length() == 10) {
                                propertyValue = DateUtils.parseDate(propertyValue.toString());
                            } else if (propertyValue.toString().length() == 19) {
                                propertyValue = DateUtils.parse(propertyValue.toString());
                            } else if (propertyValue.toString().length() == 13) {
                                propertyValue = new Date(Long.valueOf(propertyValue.toString()));
                            }
                        }
                        pd.getWriteMethod().invoke(obj, propertyValue);
                    }
                }
            }
            return (T) obj;
        }
        return null;
    }

    private static ObjectMapper mapper;
    private static JsonFactory factory;

    /**
     * 将bean、List、Map、Array转成Json字符串
     *
     * @param obj bean、List、Map、Array
     * @return json 字符串
     */
    public static String objToString(Object obj) {
        String json;
        if (null == mapper) mapper = new ObjectMapper();
        try {
            // 格式化
//			mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE); 
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("生成JSON字符串出错" + obj.getClass().getName(), e);
            json = "";
        }
        return json;
    }

    /**
     * 将bean、List、Map、Array转成Json字符串
     *
     * @param obj bean、List、Map、Array
     * @return json 字符串
     */
    public static String objToString(Object obj, String pattern) {
        String json = "";
        if (null == mapper) mapper = new ObjectMapper();
        try {
            //加上了时间格式化,以便在解析时间类型
            if (StringUtils.isNotBlank(pattern)) {
                mapper.setDateFormat(new SimpleDateFormat(pattern));
            }
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("生成JSON字符串出错" + obj.getClass().getName(), e);
            json = "";
        }
        return json;
    }

    public static <T> T stringToObj(String json, Class<T> clazz) {
        if (null == mapper) mapper = new ObjectMapper();
        if (null == factory) {
            factory = new JsonFactory(mapper);
            mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
        try {
            JsonParser jsonParser = factory.createJsonParser(json);
            return jsonParser.readValueAs(clazz);
        } catch (Exception e) {
            logger.error("" + e.getMessage(), e);
        }
        return null;
    }

    public static <T> T stringToObj(String json, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        JsonFactory factory = new JsonFactory(mapper);
        try {
            JsonParser jsonParser = factory.createJsonParser(json);
            return jsonParser.readValueAs(typeReference);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T stringToObj(String json, TypeReference<T> typeReference, String pattern) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        mapper.setDateFormat(new SimpleDateFormat(pattern));
        JsonFactory factory = new JsonFactory(mapper);
        try {
            JsonParser jsonParser = factory.createJsonParser(json);
            return jsonParser.readValueAs(typeReference);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }
}
