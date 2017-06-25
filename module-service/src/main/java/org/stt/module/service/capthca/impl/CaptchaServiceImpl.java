package org.stt.module.service.capthca.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.stt.module.constants.ExceptionConstants;
import org.stt.module.constants.StorageConstants;
import org.stt.module.dto.captcha.CaptchaCodeDto;
import org.stt.module.dto.captcha.CaptchaImageResponseDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;
import org.stt.module.service.captcha.ICaptchaService;
import org.stt.module.util.base.RandomIdGenerator;
import org.stt.module.util.cache.EhCacheUtil;
import org.stt.module.util.captcha.Captcha;
import org.stt.module.util.captcha.CaptchaBuilder;


@Service
public class CaptchaServiceImpl implements ICaptchaService {


	@Override
	public CaptchaCodeDto checkCaptchaCode(final String captchaId,
			final String captchaCode) {

		// 通用图形验证码前缀
		final String keyPrefix = "captchaCode";
		// 验证图形验证码
		checkCaptchaCode(keyPrefix, captchaId, captchaCode);

		// 验证成功返回dto
		final CaptchaCodeDto captchaCodeResult = new CaptchaCodeDto();
		captchaCodeResult.setCaptchaId(captchaId);
		captchaCodeResult.setCaptchaCode(captchaCode);

		return captchaCodeResult;
	}

	/**
	 * 生成图形验证码，将图形转换为byte形式， 并将验证码存入缓存
	 */
	@Override
	public CaptchaImageResponseDto generateCaptchaImage() {

		// 通用图形验证码前缀
		final String keyPrefix = "captchaCode";
		// 生成验证码信息
		final CaptchaImageResponseDto captchaImageDto = generateCaptchaImage(keyPrefix);

		return captchaImageDto;
	}
	
	public void checkCaptchaCode(final String keyPrefix, final String captchaId, final String captchaCode) {

		final String codeKey = keyPrefix + captchaId;
		final String savedCode = (String)EhCacheUtil.getInstance().get(StorageConstants.CAPTCHA_CACHE, codeKey);

		if (StringUtils.isEmpty(savedCode)) {
			throw new BusinessException(
					ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg(ExceptionConstants.CAPTCHA_NOT_EXISTS));
		} else {
			final boolean checkFlag = captchaCode.equalsIgnoreCase(savedCode);
			if (!checkFlag) {
				throw new BusinessException(
						ExceptionEnum.BUSINESS_CHECK_EXCP.setExceptionMsg(ExceptionConstants.CAPTCHA_CHECK_FAIL));
			}
		}
	}

	public CaptchaImageResponseDto generateCaptchaImage(final String keyPrefix) {

		final Captcha captcha = CaptchaBuilder.getInstance().build();
		// captchaCode
		final String code = captcha.getCode();
		final String captchaId = RandomIdGenerator.generateCaptchaId();
		final String codeKey = keyPrefix + captchaId;
		EhCacheUtil.getInstance().put(StorageConstants.CAPTCHA_CACHE, codeKey, code);
		byte[] bytes = null;
		try {
			bytes = captcha.getImageBytes();
		} catch (final IOException e) {
			throw new SystemException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg("获取验证码失败"));
		}

		// CaptchaImageDto
		final CaptchaImageResponseDto captchaImageDto = new CaptchaImageResponseDto();
		captchaImageDto.setCaptchaId(captchaId);
		captchaImageDto.setImageBase64(bytes);

		return captchaImageDto;
	}

}
