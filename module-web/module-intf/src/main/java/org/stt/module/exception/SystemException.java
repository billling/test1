package org.stt.module.exception;

/**
 * @author wangchao
 *
 */
public class SystemException extends RuntimeException {

    /**
     * serializable
     */
    private static final long serialVersionUID = 1L;

    private ExceptionType exceptionType;

    public SystemException(ExceptionType exceptionType) {

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

}
