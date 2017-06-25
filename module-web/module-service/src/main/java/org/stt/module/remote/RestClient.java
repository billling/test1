package org.stt.module.remote;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stt.module.constants.ExceptionConstants;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;
import org.stt.module.util.json.JsonUtil;


/**
 * @author wangchao
 *
 */
public class RestClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    public static String execute(String url , String method , Object parm){
        
        // 处理入参
        String jsonStr = handleParm(parm);
        RestExecutor executor = new RestExecutor(url,method,jsonStr);
        executor.setConnectTimeout(5000);
        executor.setSocketTimeout(5000);
        try {
            return executor.execute();
        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("RestClient error ",e);
            throw new SystemException(ExceptionEnum.EXECUTE_REMOTE_EXCP.setFormatMsg(
                    ExceptionConstants.REMOTE_CALL_FAIL, new Object[] { url, "-1" }));
        }
    }

    private static String handleParm(Object parm) {

        return JsonUtil.toString(parm);
    }
    
}
