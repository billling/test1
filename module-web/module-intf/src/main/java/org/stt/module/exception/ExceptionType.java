package org.stt.module.exception;

/**
 * 异常类型定义 <li>exceptionCode</li> <li>exceptionMsg</li>
 * @author wangchao
 *
 */
public class ExceptionType {

    /**
     * 异常码
     */
    private String exceptionCode;

    /**
     * 异常描述
     */
    private String exceptionMsg;

    public ExceptionType(String errorCode, String errorMsg) {

        this.exceptionCode = errorCode;
        this.exceptionMsg = errorMsg;
    }

    public ExceptionType(String errorCode) {

        this.exceptionCode = errorCode;
    }
    
    public String getExceptionCode() {

        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {

        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMsg() {

        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {

        this.exceptionMsg = exceptionMsg;
    }

    /**
     * 返回格式 exceptionCode + " - " + exceptionMsg
     */
    public String toString() {

        return String.valueOf(exceptionCode) + " - " + exceptionMsg;
    }

}
