package org.stt.module.exception;

import java.text.MessageFormat;

import org.apache.commons.lang3.Validate;


/**
 * 异常类型枚举
 * @author wangchao
 *
 */
public enum ExceptionEnum {

    EXECUTE_RUNTIME_EXCP(new ExceptionType("100001", "系统异常")), // 基础系统异常
    BASE_CHECK_EXCP(new ExceptionType("100002")), // 基础参数校验失败
    EXECUTE_DB_EXCP(new ExceptionType("100003")), // 数据库异常
    EXECUTE_REMOTE_EXCP(new ExceptionType("100004")), // 调用其他系统异常
    EXECUTE_LOGIN_EXCP(new ExceptionType("100005")), // 登录异常
    EXECUTE_REGIST_EXCP(new ExceptionType("100006")), // 注册异常
    EXECUTE_GETDATA_EXCP(new ExceptionType("100007", "获取数据异常")), // 获取数据异常
    EXECUTE_CACHE_EXCP(new ExceptionType("100008")), // 操作缓存异常
    EXECUTE_AUTH_EXCP(new ExceptionType("100009")), // 权限异常
	EXECUTE_CAPTCHA_EXCP(new ExceptionType("100010")), // 图形验证码异常
	EXECUTE_VERIFYCODE_EXCP(new ExceptionType("100011")), // 手机/邮箱验证码异常
	EXECUTE_ACCOUNT_VALIDATION_EXCP(new ExceptionType("100012")), // 账户校验异常
	EXECUTE_IMAGE_CHECK_EXCP(new ExceptionType("100013")), // 图片检查异常
	EXECUTE_UPLOAD_EXCP(new ExceptionType("100014")), // 文件上传异常
    BUSINESS_CHECK_EXCP(new ExceptionType("100099"));// 业务逻辑异常
    
    private ExceptionType exceptionType;

    ExceptionEnum(ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

    /**
     * 设置exceptionMsg，返回一个新的ExceptionType实例
     * @param exceptionMsg
     * @return
     */
    public ExceptionType setExceptionMsg(String exceptionMsg) {

        return new ExceptionType(exceptionType.getExceptionCode(), exceptionMsg);
    }

    /**
     * 设置格式化模板的exceptionMsg<br>
     * eg: 调用{0}系统的{1}接口失败
     * @param exceptionMsg 模板字符串
     * @param String数组 模板字符串入参
     */
    public ExceptionType setFormatMsg(String exceptionMsg, Object... obj) {

        Validate.noNullElements(obj);
        String msg = MessageFormat.format(exceptionMsg, obj);
        return new ExceptionType(exceptionType.getExceptionCode(), msg);
    }

    public ExceptionType getExceptionType() {

        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

}
