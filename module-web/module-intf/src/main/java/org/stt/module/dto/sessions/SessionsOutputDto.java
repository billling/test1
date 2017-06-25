package org.stt.module.dto.sessions;

import java.io.Serializable;

/**
 * 会话返回
 */
public class SessionsOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String sessionId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
