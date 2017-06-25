package org.stt.module.framework;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.stt.module.framework.annotation.ResponseForJson;
import org.stt.module.framework.annotation.ResponseForJson.DataType;

/**
 * 返回值处理
 * @author wangchao
 *
 */
public class ResponseForJsonProcessor implements HandlerMethodReturnValueHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseForJsonProcessor.class);

    /**
     * json消息转换器
     */
    private HttpMessageConverter jsonConverter;

    /**
     * 需要编码json消息转换器
     */
    private HttpMessageConverter encodeJsonConverter;

    private static String CHARSET = "UTF-8";

    @Override
    public void handleReturnValue(Object obj, MethodParameter methodParameter, ModelAndViewContainer container,
            NativeWebRequest nativeWebRequest) throws Exception {

        container.setRequestHandled(true);
        ResponseForJson annotation = null;
        HttpServletResponse httpServletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        ServletServerHttpResponse response = new ServletServerHttpResponse(httpServletResponse);
        if (obj != null) {
            annotation = methodParameter.getMethodAnnotation(ResponseForJson.class);
        } else {
            // FIXME 考虑返回通用的ResponseDto
        }
        if (annotation != null && annotation.dataType() == DataType.ENCODEJSON) {
            MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON,
                    Collections.singletonMap("charset", CHARSET));
            encodeJsonConverter.write(obj, mediaType, response);
        } else {
            MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON,
                    Collections.singletonMap("charset", CHARSET));
            jsonConverter.write(obj, mediaType, response);
        }

    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {

        ResponseForJson annotation = methodParameter.getMethodAnnotation(ResponseForJson.class);
        return annotation != null;
    }

    public HttpMessageConverter getJsonConverter() {

        return jsonConverter;
    }

    public void setJsonConverter(HttpMessageConverter jsonConverter) {

        this.jsonConverter = jsonConverter;
    }

    public HttpMessageConverter getEncodeJsonConverter() {

        return encodeJsonConverter;
    }

    public void setEncodeJsonConverter(HttpMessageConverter encodeJsonConverter) {

        this.encodeJsonConverter = encodeJsonConverter;
    }

    public static String getCHARSET() {

        return CHARSET;
    }

    public static void setCHARSET(String cHARSET) {

        CHARSET = cHARSET;
    }

}
