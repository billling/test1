package org.stt.module.framework.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonHttpMessageConverter.class);

    public JsonHttpMessageConverter() {

        super();
        ObjectMapper mapper = getObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {

        try {
            byte[] bytes = getObjectMapper().writeValueAsBytes(object);
            FileCopyUtils.copy(bytes, outputMessage.getBody());
        } catch (JsonProcessingException ex) {
            logger.error("JsonHttpMessageConverter error: " + ex.getMessage(), ex);
            throw new HttpMessageNotWritableException("JsonHttpMessageConverter error: " + ex.getMessage(), ex);
        }
    }
}
