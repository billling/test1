package org.stt.module.util.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 业务上下文,线程共享
 * @author wangchao
 *
 */
public class BusinessContext {

    /**
     * ThreadLocal常量，线程共享
     */
    private static final ThreadLocal<Map<String, String>> THREADLOCAL = new ThreadLocal<Map<String, String>>();

    /**
     * 获取上下文中的值
     * @param key
     * @return 获取的变量值
     */
    public static String getProperty(String key) {

        if (!StringUtils.isEmpty(key)) {
            Map<String, String> threadMap = THREADLOCAL.get();
            if (null != threadMap) {
                return threadMap.get(key);
            }
        }
        return null;
    }

    /**
     * 设置上下文中的值
     * @param key
     * @param value
     */
    public static void setProperty(String key, String value) {

        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            Map<String, String> threadMap = THREADLOCAL.get();
            if (null == threadMap) {
                threadMap = new HashMap<String, String>();
                THREADLOCAL.set(threadMap);
            }
            threadMap.put(key, value);
        }

    }

    /**
     * 清空上下文中的值
     */
    public static void clear() {

        Map<String, String> threadMap = THREADLOCAL.get();
        if (null != threadMap) {
            threadMap.clear();
        }
        
    }

}
