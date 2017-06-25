package org.stt.module.constants;
/** 
* @author  jiying 
* @date 2015年9月10日
* @version 1.0  
*/
public interface UserStatusConstants {
	
	String NEED_VERIFY = "needVerify"; //提交注册账号密码通过，等待验证短信／邮件验证码状态。

	String NEED_EXTRA_INFO = "needExtraInfo"; //  通过短信／邮箱验证码，等待提交签约信息状态。

	String NEED_APPROVAL = "needApproval"; // 已经提交签约信息，等待审核状态。

	String OK = "OK"; // 用户正常状态，已经通过签约审核。

	String REJECTED = "rejected"; // 某账户被永久拒绝提交申请。

	String RESUBMIT_EXTRA_INFO = "resubmitExtraInfo"; // 签约信息提交审核未通过，需重新提交。

	String DISABLE = "disabled"; //  用户被运营管理人员设置关闭禁止访问状态。

	String NEED_LOGIN = "needLogin"; // 所有校验请求发现当前页面没登录或登录的账号权限不足时的状态。


}
