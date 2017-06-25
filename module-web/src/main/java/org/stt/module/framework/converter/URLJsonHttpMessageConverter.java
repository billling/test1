package org.stt.module.framework.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class URLJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(URLJsonHttpMessageConverter.class);

    private String encoding = "UTF-8";
    
    public URLJsonHttpMessageConverter() {

        super();
        ObjectMapper mapper = getObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {

        try {
            String beforeStr = getObjectMapper().writeValueAsString(object);
            String afterStr = URLEncoder.encode(beforeStr, encoding);
            FileCopyUtils.copy(afterStr.getBytes(), outputMessage.getBody());
        } catch (Exception ex) {
            logger.error("URLJsonHttpMessageConverter error: " + ex.getMessage(), ex);
            throw new HttpMessageNotWritableException("URLJsonHttpMessageConverter error: " + ex.getMessage(), ex);
        }
    }
}
