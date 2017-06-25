package org.stt.module.controller.verify;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stt.module.dto.captcha.CaptchaCodeDto;
import org.stt.module.dto.captcha.CaptchaImageResponseDto;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.framework.annotation.JsonDto;
import org.stt.module.framework.annotation.ResponseForJson;
import org.stt.module.framework.annotation.ValidDto;
import org.stt.module.service.captcha.ICaptchaService;
import org.stt.module.util.base.ResponseUtil;

@Controller
@RequestMapping(value = "/manage/captcha")
public class CaptchaController {

	@Resource
	private ICaptchaService captchaService;

	@ResponseForJson
	@ValidDto
	@RequestMapping(method = RequestMethod.GET)
	public ResponseDto checkCaptchaCode(
			@JsonDto final CaptchaCodeDto captchaCodeDto) {

		final CaptchaCodeDto captchaCode = captchaService.checkCaptchaCode(
				captchaCodeDto.getCaptchaId(), captchaCodeDto.getCaptchaCode());

		return ResponseUtil.createResponseDto(captchaCode, null);
	}

	@ResponseForJson
	@RequestMapping(method = RequestMethod.POST)
	public ResponseDto getCaptchaImage() {

		final CaptchaImageResponseDto captchaImageDto = captchaService
				.generateCaptchaImage();

		return ResponseUtil.createResponseDto(captchaImageDto, null);
	}

}