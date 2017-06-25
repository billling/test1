package org.stt.module.util.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Captcha {

    private String code;

    private BufferedImage image;

    public Captcha() {
        super();
    }

    public Captcha(final String code, final BufferedImage image) {
        super();
        this.code = code;
        this.image = image;
    }

    public String getCode() {

        return code;
    }

    public void setCode(final String code) {

        this.code = code;
    }

    public BufferedImage getImage() {

        return image;
    }

    public void setImage(final BufferedImage image) {

        this.image = image;
    }

    /**
     * @return the imageBytes
     */
    public byte[] getImageBytes() throws IOException {

        if (null == image) {
            return null;
        }
        // captchaImage
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = null;
        ImageIO.write(image, CaptchaProperty.FORMAT, baos);
        bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }
}
