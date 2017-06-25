package org.stt.module.util.json;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * general json & bean converter<br>
 * add exception handle
 * @author wangchao
 *
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将json字符串转为目标对象
     * @param json 待转换字符串
     * @param obj 目标对象class
     * @return 目标对象实例
     */
    public static <T> T toBean(String json, Class<T> obj) {

        try {
            return JsonFormatter.toObject(json, obj);
        } catch (Exception e) {
            logger.error("JsonUtil.toBean error: ", e);
        }
        return null;
    }

    /**
     * 将目标对象转为json字符串
     * @param obj 目标对象
     * @return String json串
     */
    public static String toString(Object obj) {

        try {
            return JsonFormatter.toString(obj);
        } catch (Exception e) {
            logger.error("JsonUtil.toString error: ", e);
        }
        return null;
    }

    /**
     * 将json字符串转为list
     * @param jsonStr json字符串
     * @param clazz 目标对象class
     * @return
     */
    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {

        try {
            return JsonFormatter.toList(jsonStr, clazz);
        } catch (Exception e) {
            logger.error("JsonUtil.toList error: ", e);
        }
        return null;

    }

}
