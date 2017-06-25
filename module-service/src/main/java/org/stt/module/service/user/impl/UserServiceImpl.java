package org.stt.module.service.user.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.stt.module.constants.Constants;
import org.stt.module.constants.FrameConstants;
import org.stt.module.dao.user.IUserDao;
import org.stt.module.dto.user.PasswordUpdateInputDto;
import org.stt.module.dto.user.UserDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.service.sessions.ISessionsService;
import org.stt.module.service.user.IUserService;
import org.stt.module.util.log.LogRecord;
import org.stt.module.util.sessions.SessionsUtil;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	IUserDao userDao;
	
	@Resource
	ISessionsService sessionsService;

	@Override
	public UserDto getUserById(Integer id) {
		UserDto userDto = null;
		try {
			userDto = userDao.getUserById(id);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("getUserById", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("根据id查询用户信息失败"));
		}

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto userDto = null;
		try {
			userDto = userDao.getUserByUserId(userId);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("getUserByUserId", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("根据用户id查询用户信息失败"));
		}

		return userDto;
	}

	@Override
	public int createUser(UserDto userDto) {
		int resp = -1;
		try {
			String password = DigestUtils.md5Hex(userDto.getPassword() + Constants.SALT);
			userDto.setPassword(password);
			resp = userDao.createUser(userDto);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("createUser", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("用户信息创建失败"));
		}

		return resp;
	}

	@Override
	public int updateUser(UserDto userDto) {
		int resp = -1;
		try {
			resp = userDao.updateUser(userDto);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("updateUser", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("用户信息更新失败"));
		}

		return resp;
	}

	@Override
	public int deleteUser(String userId) {
		int resp = -1;
		try {
			resp = userDao.deleteUser(userId);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("createUser", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("用户信息删除失败"));
		}

		return resp;
	}

	@Override
	public int updatePassword(PasswordUpdateInputDto passwordUpdateInputDto) {
		int resp = -1;
		final HttpSession newSession = SessionsUtil.getCurrentSession();
		String userId = String.valueOf(newSession.getAttribute(FrameConstants.USER_ID));
		UserDto userDto = new UserDto();
		userDto = userDao.getUserByUserId(userId);
		final Boolean passwordCheckFlag = SessionsUtil.checkPassword(passwordUpdateInputDto.getOldPassword(), userDto.getPassword());
		if (!passwordCheckFlag) {
			throw new BusinessException(ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg("原密码错误"));
		}
		userDto.setPassword(DigestUtils.md5Hex(passwordUpdateInputDto.getNewPassword() + Constants.SALT));
		try {
			resp = userDao.updatePassword(userDto);
		} catch (final Exception e) {
			LogRecord.logErrorRecord("updatePassword", UserServiceImpl.class, e);
			throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("修改密码失败"));
		}
		return resp;
	}

	
}
