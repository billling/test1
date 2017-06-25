package org.stt.module.util.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stt.module.constants.ExceptionConstants;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.util.json.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Validate util
 * @author wangchao
 *
 */
public class ValidateUtil {

    /**
     * 通用mapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

    static {
        // json->bean ,忽略bean中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // bean->json ,忽略为null的属性
        mapper.setSerializationInclusion(Include.NON_NULL);
        // map->json ,忽略为null的key
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    /**
     * validate Json string
     * @param jsonData
     * @param type
     * @return boolean
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws BusinessException
     */
    public static <T> T validateJson(String jsonData, Class<T> clazz) throws JsonParseException, JsonMappingException,
            IOException, BusinessException {

        // string -> object
        T object = JsonUtil.toBean(jsonData, clazz);
        Validate.notNull(object);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<ConstraintViolation<T>> it = violations.iterator();
            while (it.hasNext()) {
                ConstraintViolation<T> violation = it.next();
                buffer.append(violation.getPropertyPath()).append(":").append(violation.getMessage()).append(" ,");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            logger.debug(buffer.toString());

            // 参数验证失败 抛出自定义异常
            throw new BusinessException(ExceptionEnum.BASE_CHECK_EXCP.setFormatMsg(ExceptionConstants.PARMS_CHECK_FAIL,
                    buffer.toString()));
        }
        return object;
    }

}
