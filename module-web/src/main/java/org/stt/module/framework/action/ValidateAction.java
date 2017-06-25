package org.stt.module.framework.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.stt.module.constants.ExceptionConstants;
import org.stt.module.dto.common.ClientErrorDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.framework.annotation.ValidDto;
import org.stt.module.util.json.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 参数预处理
 * @author wangchao
 *
 */
public class ValidateAction implements IWorkAction {

    private static final Logger logger = LoggerFactory.getLogger(ValidateAction.class);

    private static String ILLEGAL_STR = "{}";

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
    public void after(ActionContext context) {

        // nothing to do
    }

    @Override
    public boolean doAction(ActionContext context) throws IOException {

        HandlerMethod handlerMethod = context.getHandlerMethod();
        String jsonData = getData(context.getRequest());
        return validateJson(jsonData, handlerMethod);
    }

    private String getData(HttpServletRequest request) throws IOException {

        String method = request.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            return request.getQueryString();
        }
        StringBuilder buffer = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private void validateObject(Object validObject) {

        Validate.notNull(validObject);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(validObject);
        if (!violations.isEmpty()) {
            Iterator<ConstraintViolation<Object>> it = violations.iterator();
            ClientErrorDto clientDto = new ClientErrorDto();
            while (it.hasNext()) {
                ConstraintViolation<Object> violation = it.next();
                clientDto.getFieldErrorMessage().put(String.valueOf(violation.getPropertyPath()),
                        violation.getMessage());
            }
            String errorMsg = JsonUtil.toString(clientDto);
            logger.debug(errorMsg);

            // 参数验证失败 抛出自定义异常
            throw new BusinessException(ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg(errorMsg));
        }
    }

    public boolean validateJson(String jsonData, HandlerMethod handlerMethod) throws IOException {

        ValidDto validDto = handlerMethod.getMethodAnnotation(ValidDto.class);
        if (validDto != null) {
            if (!StringUtils.isEmpty(jsonData) && !ILLEGAL_STR.equals(jsonData)) {
                MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
                /*
                 * for (MethodParameter methodParameter : methodParameters) {
                 * Object validObject = mapper.readValue(jsonData,
                 * mapper.getTypeFactory
                 * ().constructType(methodParameter.getParameterType()));
                 * validateObject(validObject); }
                 */
                // 只校验第一个入参
                Object validObject = mapper.readValue(jsonData,
                        mapper.getTypeFactory().constructType(methodParameters[0].getParameterType()));
                validateObject(validObject);
            } else {
                throw new BusinessException(ExceptionEnum.BASE_CHECK_EXCP.setFormatMsg(
                        ExceptionConstants.PARMS_CHECK_FAIL, "入参不能为空"));
            }
        }
        return true;
    }

    @Override
    public boolean isMatch(ActionContext context) {

        ValidDto validDto = context.getHandlerMethod().getMethodAnnotation(ValidDto.class);
        return validDto != null;
    }

}
