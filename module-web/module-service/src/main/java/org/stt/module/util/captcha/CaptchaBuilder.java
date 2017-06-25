package org.stt.module.util.captcha;


public class CaptchaBuilder {

    private static class InstanceHolder {

        private static final CaptchaBuilder captchaBuilder = new CaptchaBuilder();
    }

    public static CaptchaBuilder getInstance() {

        return InstanceHolder.captchaBuilder;
    }

    /**
     * 画是否要画干扰线
     *
     * @param isDrawRandomLine
     * @return CaptchaBuilder
     */
    public CaptchaBuilder drawRandomLine(final boolean isDrawRandomLine) {

        CaptchaUtil.isRandomLine = isDrawRandomLine;
        return this;
    }

    /**
     * 设置是否要画噪点
     *
     * @param isDrawRandomNoise
     * @return CaptchaBuilder
     */
    public CaptchaBuilder drawRandomNoise(final boolean isDrawRandomNoise) {

        CaptchaUtil.isRandomNoise = isDrawRandomNoise;
        return this;
    }

    /**
     * 设置是否要扭曲背景
     *
     * @param isShearBackground
     * @return CaptchaBuilder
     */
    public CaptchaBuilder shearBackground(final boolean isShearBackground) {

        CaptchaUtil.isShearBackground = isShearBackground;
        return this;
    }

    public Captcha build() {

        final int imageWidth = CaptchaProperty.WIDTH;
        final int imageHeight = CaptchaProperty.HEIGHT;
        final int codeCount = CaptchaProperty.COUNT;
        final String fontFace = CaptchaProperty.FONT;
        return CaptchaUtil.generateCaptcha(imageWidth, imageHeight, codeCount, fontFace);
    }
}
