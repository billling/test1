package org.stt.module.annotations;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * DateTimeParse for jackson<br>
 * used in field annotations
 * @author wangchao
 *
 */
public class JackJsonDateTimeParse extends JsonDeserializer<Date> {

    private static final Logger LOG = LoggerFactory.getLogger(JackJsonDateTimeParse.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = jp.getText();
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        if (19 == date.trim().length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        if (16 == date.trim().length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        if (10 == date.trim().length() && date.trim().contains("-")) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        if (5 == date.trim().length() && date.trim().contains(":")) {
            format = new SimpleDateFormat("HH:mm");
        }
        try {
            return format.parse(date);
        } catch (Exception e) {
            LOG.error("jackson datetime parse error", e);
        }
        return null;
    }
}
