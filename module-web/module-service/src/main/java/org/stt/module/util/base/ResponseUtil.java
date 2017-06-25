package org.stt.module.util.base;


import org.stt.module.constants.FrameConstants;
import org.stt.module.dto.common.ClientErrorDto;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.ExceptionType;
import org.stt.module.util.json.JsonUtil;


/**
 * 工具类
 */
public class ResponseUtil {

    private static final String SUCCESS = "SUCCESS";

    private static final String CLIENT_ERROR = "CLIENT_ERROR";

    private static final String SERVER_ERROR = "SERVER_ERROR";

    private static final String BUSINESS_ERROR = "BUSINESS_ERROR";

    private static final String CLIENT_ERROR_CODE = ExceptionEnum.BASE_CHECK_EXCP.getExceptionType().getExceptionCode();

    private static final String BUSINESS_ERROR_CODE = ExceptionEnum.BUSINESS_CHECK_EXCP.getExceptionType()
            .getExceptionCode();
    /**
     * 拼装返回的responseDto
     *
     * @param result 需要返回的值
     * @param excp 异常
     * @param userStatus
     * @return
     */
    public static ResponseDto createResponseDto(final Object result, final ExceptionType excp, final String userStatus) {

        final ResponseDto dto = new ResponseDto();
        dto.setUserStatus(userStatus);
        dto.setCallId(BusinessContext.getProperty(FrameConstants.CALL_ID));
        if (excp != null) {
            dto.setMessage(excp.getExceptionMsg());
            if (CLIENT_ERROR_CODE.equals(excp.getExceptionCode())) {
                dto.setStatus(CLIENT_ERROR);
                dto.setMessage(JsonUtil.toBean(excp.getExceptionMsg(), ClientErrorDto.class));
            } else if (BUSINESS_ERROR_CODE.equals(excp.getExceptionCode())) {
                dto.setStatus(BUSINESS_ERROR);
                // dto.setMessage(excp.getExceptionMsg());
            } else {
                dto.setStatus(SERVER_ERROR);
            }
            dto.setResult(null);
        } else {
            dto.setStatus(SUCCESS);
            dto.setResult(result);
        }
        return dto;

    }

    /**
     * 拼装返回的responseDto 默认不使用userStatus
     *
     * @param result 需要返回的值
     * @param excp 异常
     * @return
     */
    public static ResponseDto createResponseDto(final Object result, final ExceptionType excp) {

        return createResponseDto(result, excp, null);

    }



}
