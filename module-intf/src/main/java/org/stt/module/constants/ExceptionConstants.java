package org.stt.module.constants;

/**
 * 自定义异常错误msg定义
 * @author wangchao
 *
 */
public interface ExceptionConstants {

    String PARMS_CHECK_FAIL = "参数校验失败: {0}";
    String LOGIN_FAIL = "登录失败: {0}";
    String REGIST_FAIL = "注册失败: {0}";
    
    String DEVELOPER_ALREADY_EXISTS = "用户名已存在";
    String DEVELOPER_LOGIN_FAIL = "用户名或者密码错误";
    
    String REMOTE_CALL_FAIL = "远程调用失败 , URL:{0} | 状态码: {1}";
    
    String CAPTCHA_CHECK_FAIL = "图片验证码验证错误";
    String CAPTCHA_NOT_EXISTS = "图片验证码不存在";
    String VERIFYCODE_CHECK_FAIL = "验证码验证错误";
    String VERIFYCODE_NOT_EXISTS = "验证码不存在";
    String ACCOUNT_ILLEGAL = "用户名格式错误";
    String ACCOUNT_ALREADY_EXISTS = "用户名已存在";
    String IMAGE_ERROR = "图片格式错误";
    String ACCOUNT_NOT_EXISTS = "用户不存在";
    String UPLAOD_FAIL = "上传失败";
}
