package org.stt.module.dto.common;

/**
 * 通用返回信息封装
 * @author luyao
 *
 */
public class ResponseDto {

    /**
     * 响应状态
     */
    private String status;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 调用id
     */
    private String callId;

    /**
     * 响应信息
     */
    private Object message;

    /**
     * 响应结果
     */
    private Object result;

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getUserStatus() {

        return userStatus;
    }

    public void setUserStatus(String userStatus) {

        this.userStatus = userStatus;
    }

    public String getCallId() {

        return callId;
    }

    public void setCallId(String callId) {

        this.callId = callId;
    }

    public Object getMessage() {

        return message;
    }

    public void setMessage(Object message) {

        this.message = message;
    }

    public Object getResult() {

        return result;
    }

    public void setResult(Object result) {

        this.result = result;
    }

}
