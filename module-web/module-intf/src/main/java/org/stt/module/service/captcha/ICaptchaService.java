package org.stt.module.service.captcha;

import org.stt.module.dto.captcha.CaptchaCodeDto;
import org.stt.module.dto.captcha.CaptchaImageResponseDto;

public interface ICaptchaService {
	CaptchaImageResponseDto generateCaptchaImage();

	CaptchaCodeDto checkCaptchaCode(String codeKey, String capthaCode);
}
