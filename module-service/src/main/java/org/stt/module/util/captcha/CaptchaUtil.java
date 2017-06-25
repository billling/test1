package org.stt.module.util.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jiying
 *
 */

public class CaptchaUtil {

	public static boolean isRandomLine = true;
	public static boolean isRandomNoise = false;
	public static boolean isShearBackground = false;

	// 字符集，排除0和O
	private static char[] codeOption = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static Random random = new Random();

	private static List<String> fontList = new ArrayList<String>();

	static {
		final String[] fonts = { "Bodoni MT", "Times New Roman", "Palatino Linotype", "DFKai-SB", "Lucida Console",
				"Verdana", "Traditional Arabic", "Meiryo UI", "Malgun Gothic", "Century" };
		Font font = null;
		for (int i = 0; i < fonts.length; i++) {
			font = new Font(fonts[i], Font.ITALIC, 5);
			if (font.canDisplay('A')) {
				fontList.add(fonts[i]);
			}
		}
	}

	// 生成验证码图形
	public static Captcha generateCaptcha(final int imageWidth, final int imageHeight, final int codeCount,
			String fontFace) {

		final int lineCount = 20; // 干扰线数目
		final float noiseRate = 0.02f; // 噪点率
		if ("random".equalsIgnoreCase(fontFace)) {
			final String font = getRandomFont();
			if (null != font) {
				fontFace = font;
			}
		} else if (!fontList.contains(fontFace)) {
			fontFace = "Algerian"; // 默认字体
		}

		final BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = image.createGraphics();
		// 背景色，浅色
		final Color backgroundColor = getRandomLightColor();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, imageWidth, imageHeight);
		// 边框
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, imageWidth - 1, imageHeight - 1);
		// 干扰线
		if (isRandomLine) {
			drawRandomLine(g, imageWidth, imageHeight, lineCount);
		}
		// 噪点
		if (isRandomNoise) {
			drawRandomNoise(image, imageWidth, imageHeight, noiseRate);
		}
		// 扭曲背景
		if (isShearBackground) {
			shearBackground(g, imageWidth, imageHeight, backgroundColor);
		}
		// 随机字符
		final String randomCode = generateCaptchaCode(codeCount);
		// 绘制随机字符
		drawRandomCode(g, imageWidth, imageHeight, randomCode, fontFace);
		g.dispose();

		return new Captcha(randomCode, image);

	}

	// 生成随机验证码
	private static String generateCaptchaCode(final int codeCount) {

		final StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < codeCount; i++) {
			final String randomStr = String.valueOf(codeOption[random.nextInt(codeOption.length)]);
			randomCode.append(randomStr);
		}
		return randomCode.toString();
	}

	// 生成随机颜色
	private static Color getRandomColor() {

		int r, g, b;
		r = random.nextInt(256);
		g = random.nextInt(256);
		b = random.nextInt(256);
		return new Color(r, g, b);
	}

	// 获取浅随机色
	private static Color getRandomLightColor() {

		int r, g, b;
		r = 192 + random.nextInt(64);
        g = 192 + random.nextInt(64);
        b = 192 + random.nextInt(64);

		return new Color(r, g, b);
	}

	// 获取浅随机色
	private static Color getRandomDeepColor() {

		int r, g, b;
		final double dec = 0.5;
		r = random.nextInt(256);
		g = random.nextInt(256);
		b = random.nextInt(256);
		while (r * 0.299 + g * 0.587 + b * 0.114 > 64) {
			r *= dec;
			g *= dec;
			b *= dec;
		}
		return new Color(r, g, b);
	}

	// 绘制干扰线,随机长度，随机位置
	private static void drawRandomLine(final Graphics2D g, final int imageWidth, final int imageHeight,
			final int lineCount) {

		final int lineLength = (int) (0.8 * imageHeight);
		for (int i = 0; i < lineCount; i++) {
			final int x = random.nextInt(imageWidth);
			final int y = random.nextInt(imageHeight);
			int xl = x + random.nextInt(lineLength) * (random.nextBoolean() ? 1 : -1);
			int yl = y + random.nextInt(lineLength) * (random.nextBoolean() ? 1 : -1);
			if (xl < 0) {
				xl = 0;
			}
			if (xl > imageWidth) {
				xl = imageWidth;
			}
			if (yl < 0) {
				yl = 0;
			}
			if (yl > imageHeight) {
				yl = imageHeight;
			}
			g.setColor(getRandomColor());
			g.drawLine(x, y, xl, yl);
		}

	}

	// 绘制随机噪点
	private static void drawRandomNoise(final BufferedImage image, final int imageWidth, final int imageHeight,
			final float noiseRate) {

		final int noiseCount = (int) (imageWidth * imageHeight * noiseRate);
		for (int i = 0; i < noiseCount; i++) {
			final int x = random.nextInt(imageWidth);
			final int y = random.nextInt(imageHeight);
			final Color c = getRandomColor();
			image.setRGB(x, y, c.getRGB());

		}

	}

	// 扭曲背景
	private static void shearBackground(final Graphics g, final int imageWidth, final int imageHeight,
			final Color color) {

		shearX(g, imageWidth, imageHeight, color);
		shearY(g, imageWidth, imageHeight, color);
	}

	private static void shearX(final Graphics g, final int imageWidth, final int imageHeight, final Color color) {

		final int period = random.nextInt(2);

		final boolean borderGap = true;
		final int frames = 1;
		final int phase = random.nextInt(2);

		for (int i = 0; i < imageHeight; i++) {
			final double d = (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
			g.copyArea(0, i, imageWidth, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + imageWidth, i, imageWidth, i);
			}
		}

	}

	private static void shearY(final Graphics g, final int imageWidth, final int imageHeight, final Color color) {

		final int period = random.nextInt(imageWidth / 5);

		final boolean borderGap = true;
		final int frames = 20;
		final int phase = 7;
		for (int i = 0; i < imageWidth; i++) {
			final double d = (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
			g.copyArea(i, 0, 1, imageHeight, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + imageHeight, i, imageHeight);
			}

		}

	}

	// 获取随机本地字体
	private static String getRandomFont() {

		if (fontList.isEmpty()) {
			return null;
		}
		final String randomFont = fontList.get(random.nextInt(fontList.size()));
		return randomFont;
	}

	// 绘制随机字符
	private static void drawRandomCode(final Graphics2D g, final int imageWidth, final int imageHeight,
			final String randomCode, final String fontFace) {

		final int codeCount = randomCode.length();
		final int codeSpace = imageWidth / (codeCount + 1); // 字符宽度
		final int fontHeight = (int) (0.9 * imageHeight); // 字符高度
		final int codeBase = fontHeight; // 字符基线

		final Font codeFont = new Font(fontFace, Font.ITALIC, fontHeight);
		g.setFont(codeFont);
		for (int i = 0; i < randomCode.length(); i++) {
			final AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1),
					(imageWidth / codeCount) * i + fontHeight / 2, imageHeight / 2);
			g.setTransform(affine);
			final String randomStr = String.valueOf(randomCode.charAt(i));
			g.setColor(getRandomDeepColor());
			g.drawString(randomStr, codeSpace * i + codeSpace / 2, codeBase);
		}

	}
}
