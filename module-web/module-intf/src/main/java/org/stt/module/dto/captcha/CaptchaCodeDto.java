package org.stt.module.dto.captcha;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CaptchaCodeDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String captchaId;

	@NotBlank
	private String captchaCode;

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
