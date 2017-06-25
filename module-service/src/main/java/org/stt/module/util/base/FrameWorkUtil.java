package org.stt.module.util.base;

import org.stt.module.constants.FrameConstants;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.exception.ExceptionType;

/**
 * 工具类
 * @author wangchao
 *
 */
public class FrameWorkUtil {

    private static final String SUCCESS = "SUCCESS";

    private static final String CLIENT_ERROR = "CLIENT_ERROR";

    private static final String SERVER_ERROR = "SERVER_ERROR";

    private static final String CLIENT_ERROR_CODE = "100002";

    /**
     * 拼装返回的responseDto
     * @param result 需要返回的值
     * @param excp 异常
     * @param userStatus
     * @return
     */
    public static ResponseDto createResponseDto(Object result, ExceptionType excp, String userStatus) {

        ResponseDto dto = new ResponseDto();
        dto.setUserStatus(userStatus);
        dto.setCallId(BusinessContext.getProperty(FrameConstants.CALL_ID));
        if (excp != null) {
            if (CLIENT_ERROR_CODE.equals(excp.getExceptionCode())) {
                dto.setStatus(CLIENT_ERROR);
            }else {
                dto.setStatus(SERVER_ERROR);
            }
            dto.setResult(null);
            dto.setMessage(excp.getExceptionMsg());
            
        } else {
            dto.setStatus(SUCCESS);
            dto.setResult(result);
        }
        return dto;

    }

    /**
     * 拼装返回的responseDto 默认不使用userStatus
     * @param result 需要返回的值
     * @param excp 异常
     * @return
     */
    public static ResponseDto createResponseDto(Object result, ExceptionType excp) {

        return createResponseDto(result, excp, null);

    }

}
