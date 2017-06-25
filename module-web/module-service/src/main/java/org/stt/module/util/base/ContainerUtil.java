package org.stt.module.util.base;

import java.util.List;

/**
 * container util
 * @author guolijuan
 *
 */
public class ContainerUtil {

    public static boolean isEmpty(Object[] arr){
        if (arr == null || arr.length == 0)
            return true;
        
        return false;
    }
    
    public static boolean isNotEmpty(Object[] arr){
        return !isEmpty(arr);
    }
    
    public static <T> boolean isEmpty(List<T> list){
        if(list == null || list.size() == 0)
            return true;
        
        return false;
    }

    public static <T> boolean isNotEmpty(List<T> list){
        return !isEmpty(list);
    }
}
