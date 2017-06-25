package org.stt.module.util.sessions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.stt.module.constants.Constants;

public class SessionsUtil {
	public static HttpSession createNewSession() {

		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		final HttpSession session = request.getSession();
		return session;
	}

	public static HttpSession getCurrentSession() {

		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		final HttpSession session = request.getSession(false);
		return session;
	}

	public static String invalidateCurrentSession() {

		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		final HttpSession session = request.getSession(false);
		final String sessionId = session.getId();
		session.invalidate();
		return sessionId;

	}

	public static boolean checkPassword(final String currentPassword,
			final String truePassword) {

		final String currentPasswordMD5 = DigestUtils.md5Hex(currentPassword + Constants.SALT);
		return truePassword.equals(currentPasswordMD5);
	}
}
