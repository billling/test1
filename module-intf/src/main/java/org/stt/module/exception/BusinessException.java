package org.stt.module.exception;

/**
 * @author wangchao
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * serializable
     */
    private static final long serialVersionUID = 1L;

    private ExceptionType exceptionType;

    public BusinessException(ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {

        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {

        this.exceptionType = exceptionType;
    }

    public static long getSerialversionuid() {

        return serialVersionUID;
    }

    /**
     * 获取异常码
     * @return
     */
    public String getExceptionCode() {

        return this.exceptionType.getExceptionCode();
    }

    /**
     * 获取异常描述
     * @return
     */
    public String getExceptionMsg() {

        return this.exceptionType.getExceptionMsg();
    }

}
