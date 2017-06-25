package org.stt.module.dto.sessions;

import java.io.Serializable;

/**
 *
 * @author luyao
 *
 *         2015年9月18日
 */
public class SessionsInputDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private String password;

	private String captchaId;

	private String captchaCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

	public String getCaptchaId() {

		return captchaId;
	}

	public void setCaptchaId(final String captchaId) {

		this.captchaId = captchaId;
	}

	public String getCaptchaCode() {

		return captchaCode;
	}

	public void setCaptchaCode(final String captchaCode) {

		this.captchaCode = captchaCode;
	}

}
