package org.stt.module.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomIdGenerator {

	public static String generateCaptchaId() {
		final UUID uuid = UUID.randomUUID();
		final String id = uuid.toString().replace("-", "");
		final Date date = new Date();
		final SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMddHHmmssSS");
		final String time = formatter.format(date);
		final String randomId = time + id;
		return randomId;
	}

}
