package org.stt.module.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;
import org.stt.module.dto.common.ClientErrorDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.framework.annotation.JsonDto;
import org.stt.module.util.base.BeanUtil;
import org.stt.module.util.json.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 入参处理器
 * @author wangchao
 *
 */
public class JsonDtoArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(JsonDtoArgumentResolver.class);

    /**
     * 通用mapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // json->bean ,忽略bean中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // bean->json ,忽略为null的属性
        mapper.setSerializationInclusion(Include.NON_NULL);
        // map->json ,忽略为null的key
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {

        return parameter.hasParameterAnnotation(JsonDto.class);

    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        final String jsonData = getData(httpServletRequest);
        Object arg = null;
        try {
            arg = convertArgs(parameter, mavContainer, webRequest, jsonData);

        } catch (final Exception e) {
            final ClientErrorDto error = new ClientErrorDto();
            error.setCrossFieldErrorMessage("入参格式不正确");
            throw new BusinessException(ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg(JsonUtil.toString(error)));
        }

        return arg;
    }

    private Object convertArgs(final MethodParameter parameter, final ModelAndViewContainer mavContainer,// NOPMD
            final NativeWebRequest webRequest, final String jsonData) throws Exception, IOException,
            JsonParseException,// NOPMD
            JsonMappingException {

        final Class<?> paramType = parameter.getParameterType();
        Object result = null;
        // 如果是参数是简单数据类型,进行赋值
        if (BeanUtil.isSimpleType(paramType)) {

            final Object arg = resolveName(parameter.getParameterName(), parameter, webRequest);
            result = BeanUtil.convertToSimpleType(String.valueOf(arg), paramType);
        } else {
            // 如果是结构体,进行json转换
            if (!StringUtils.isEmpty(jsonData)) {

                result = mapper
                        .readValue(jsonData, mapper.getTypeFactory().constructType(parameter.getParameterType()));
            }
        }
        return result;
    }

    private String getData(final HttpServletRequest request) throws IOException {

        final String method = request.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            return request.getQueryString();
        }
        final StringBuilder buffer = new StringBuilder();
        String line;
        final BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    @SuppressWarnings("unchecked")
    private Object resolveName(final String name, final MethodParameter parameter, final NativeWebRequest request)// NOPMD
            throws Exception {// NOPMD

        final Map<String, String> uriTemplateVars = (Map<String, String>) request.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return (uriTemplateVars != null) ? uriTemplateVars.get(name) : null;
    }

}
