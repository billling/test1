package org.stt.module.service.sessions.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.stt.module.constants.FrameConstants;
import org.stt.module.dao.user.IUserDao;
import org.stt.module.dto.sessions.SessionsInputDto;
import org.stt.module.dto.sessions.SessionsOutputDto;
import org.stt.module.dto.user.UserDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.service.captcha.ICaptchaService;
import org.stt.module.service.sessions.ISessionsService;
import org.stt.module.util.base.BusinessContext;
import org.stt.module.util.log.LogRecord;
import org.stt.module.util.sessions.SessionsUtil;


@Service
public class SessionsServiceImpl implements ISessionsService {

	@Resource
	private ICaptchaService captchaService;
	
	@Resource
	private IUserDao userDao;

	@Override
	public String userLogout() {
		
		return SessionsUtil.invalidateCurrentSession();
	}

	@Override
	public SessionsOutputDto userLogin(final SessionsInputDto sessionsInputDto) {

		final SessionsOutputDto sessionsOutputDto = new SessionsOutputDto();
		if (null == captchaService.checkCaptchaCode(sessionsInputDto.getCaptchaId(),sessionsInputDto.getCaptchaCode())) {
			throw new BusinessException(ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("验证码不正确"));
		}
		final UserDto userDto = userDao.getUserByUserId(sessionsInputDto.getUserId());
		if (null == userDto) {
			throw new BusinessException(ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("账号或密码错误"));
		} else {
			final Boolean passwordCheckFlag = SessionsUtil.checkPassword(sessionsInputDto.getPassword(), userDto.getPassword());
			if (!passwordCheckFlag) {
					throw new BusinessException(ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("账号或密码错误"));
			} else {
				try {
					final HttpSession newSession = SessionsUtil.createNewSession();
					newSession.setAttribute(FrameConstants.USER_ID,userDto.getUserId());
					newSession.setAttribute(FrameConstants.USER_NAME,userDto.getUserName());
					newSession.setAttribute(FrameConstants.CALL_ID,BusinessContext.getProperty(FrameConstants.CALL_ID));
					sessionsOutputDto.setSessionId(newSession.getId());
					sessionsOutputDto.setUserId(userDto.getUserId());
					sessionsOutputDto.setUserName(userDto.getUserName());
				} catch (final Exception e) {
					LogRecord.logErrorRecord("用户登录异常",SessionsServiceImpl.class,e);
				}
			}
		}

		return sessionsOutputDto;

	}

}
