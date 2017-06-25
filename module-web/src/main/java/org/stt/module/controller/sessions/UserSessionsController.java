package org.stt.module.controller.sessions;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stt.module.constants.UserStatusConstants;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.dto.sessions.SessionsInputDto;
import org.stt.module.dto.sessions.SessionsOutputDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;
import org.stt.module.framework.annotation.JsonDto;
import org.stt.module.framework.annotation.ResponseForJson;
import org.stt.module.framework.annotation.ValidDto;
import org.stt.module.service.sessions.ISessionsService;
import org.stt.module.util.base.ResponseUtil;

/**
 * 用户会话
 */
@Controller
@RequestMapping(value = "/manage/sessions")
public class UserSessionsController {

    @Resource
    ISessionsService sessionsService;

    @ResponseForJson
    @ValidDto
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDto userLogin(@JsonDto final SessionsInputDto sessionsInputDto) {

        SessionsOutputDto sessionsOutputDto = new SessionsOutputDto();
        try {
        	sessionsOutputDto = sessionsService.userLogin(sessionsInputDto);
        } catch (final BusinessException e) {
            return ResponseUtil.createResponseDto(null, e.getExceptionType());
        } catch (final SystemException e) {
            return ResponseUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return ResponseUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return ResponseUtil.createResponseDto(sessionsOutputDto, null, UserStatusConstants.OK);
    }


    @ResponseForJson
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseDto userLogout() {

        final SessionsOutputDto sessionsOutputDto = new SessionsOutputDto();
        try {
            final String sessionId = sessionsService.userLogout();
            sessionsOutputDto.setSessionId(sessionId);
        } catch (final SystemException e) {
            return ResponseUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return ResponseUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return ResponseUtil.createResponseDto(sessionsOutputDto, null);
    }
}
