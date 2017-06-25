package org.stt.module.dto.user;

import java.io.Serializable;

public class PasswordUpdateInputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oldPassword;
	
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}



}
