package org.stt.module.util.base;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

/**
 * bean工具类
 *
 * @author wangchao
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class BeanUtil {

    private static final Set<Class<?>> SIMPLECLASS = new HashSet<Class<?>>();

    static {
        SIMPLECLASS.add(byte.class);
        SIMPLECLASS.add(Byte.class);
        SIMPLECLASS.add(Short.class);
        SIMPLECLASS.add(short.class);
        SIMPLECLASS.add(int.class);
        SIMPLECLASS.add(Integer.class);
        SIMPLECLASS.add(long.class);
        SIMPLECLASS.add(Long.class);
        SIMPLECLASS.add(Float.class);
        SIMPLECLASS.add(float.class);
        SIMPLECLASS.add(Double.class);
        SIMPLECLASS.add(double.class);
        SIMPLECLASS.add(String.class);
        SIMPLECLASS.add(char.class);
        SIMPLECLASS.add(Boolean.class);
        SIMPLECLASS.add(boolean.class);
    }

    /**
     * 将源bean属性拷贝到目标bean
     *
     * @param sourceBean
     * @param targetBean
     */
    public static void copyProperties(final Object sourceBean, final Object targetBean) {

        BeanUtils.copyProperties(sourceBean, targetBean);
    }

    /**
     * 将目标Class实例化成bean并将源bean的属性设置到目标bean
     *
     * @param sourceBean
     * @param targetClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T convertProperties(final Object sourceBean, final Class<T> targetClass)
            throws InstantiationException, IllegalAccessException {

        T targetBean = null;
        // try {
        targetBean = targetClass.newInstance();
        BeanUtils.copyProperties(sourceBean, targetBean);
        // } catch (Exception e) {
        // throw new
        // SystemException(ExceptionEnum.EXECUTE_BEAN_COPY.setExceptionMsg("bean转换异常"));
        // }
        return targetBean;
    }

    /**
     * 将目标Class实例化成bean并将源bean的属性设置到目标bean<br>
     * 只拷贝editable中的属性
     *
     * @param sourceBean
     * @param targetClass
     * @param editable 用来限制拷贝的属性
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T convertProperties(final Object sourceBean, final Class<T> targetClass, final Class<T> editable)
            throws InstantiationException, IllegalAccessException {

        T targetBean = null;
        // try {
        targetBean = targetClass.newInstance();
        BeanUtils.copyProperties(sourceBean, targetBean, editable);
        // } catch (final Exception e) {
        // throw new
        // SystemException(ExceptionEnum.EXECUTE_BEAN_COPY.setExceptionMsg("bean转换异常"));
        // }
        return targetBean;
    }

    /**
     * 将目标Class实例化成bean并将源bean的属性设置到目标bean<br>
     * 忽略ignoreProperties中定义的属性
     *
     * @param sourceBean
     * @param targetClass
     * @param ignoreProperties
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T convertProperties(final Object sourceBean, final Class<T> targetClass,
            final String[] ignoreProperties) throws InstantiationException, IllegalAccessException {

        T targetBean = null;
        // try {
        targetBean = targetClass.newInstance();
        BeanUtils.copyProperties(sourceBean, targetBean, ignoreProperties);
        // } catch (final Exception e) {
        // throw new
        // SystemException(ExceptionEnum.EXECUTE_BEAN_COPY.setExceptionMsg("bean转换异常"));
        // }
        return targetBean;
    }

    /**
     * 判断是否为基础数据类型<br>
     *
     * @param Class <?> T
     * @return
     */
    public static boolean isSimpleType(final Class<?> T) {

        return Object.class.equals(T) || SIMPLECLASS.contains(T);

    }

    /**
     * 将字符串源对象转换成目标基本类型
     *
     * @param sourceBean
     * @param T
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToSimpleType(final String sourceBean, final Class<?> T) {

        T result = null;
        if (T == Byte.class || T == byte.class) {
            result = (T) Byte.valueOf(sourceBean);
        } else if (T == Short.class || T == short.class) {
            result = (T) Short.valueOf(sourceBean);
        } else if (T == Integer.class || T == int.class) {
            result = (T) Integer.valueOf(sourceBean);
        } else if (T == Long.class || T == long.class) {
            result = (T) Long.valueOf(sourceBean);
        } else if (T == Float.class || T == float.class) {
            result = (T) Float.valueOf(sourceBean);
        } else if (T == Double.class || T == double.class) {
            result = (T) Double.valueOf(sourceBean);
        } else if (T == String.class || T == char.class) {
            result = (T) String.valueOf(sourceBean);
        } else if (T == Boolean.class || T == boolean.class) {
            result = (T) Boolean.valueOf(sourceBean);
        }
        return result;
    }
}
