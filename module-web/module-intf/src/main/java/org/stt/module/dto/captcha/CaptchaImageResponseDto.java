package org.stt.module.dto.captcha;

import java.io.Serializable;

public class CaptchaImageResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] imageBase64;

	private String captchaId;

	public byte[] getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(final byte[] imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public String getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(final String captchaId) {
		this.captchaId = captchaId;
	}

}
