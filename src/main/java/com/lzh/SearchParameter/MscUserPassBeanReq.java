package com.lzh.SearchParameter;

public class MscUserPassBeanReq {

	/**
	 * 用户原密码
	 */
	private String oldPassword;

	/**
	 * 用户密码
	 */
	private String newPassword;

	/**
	 * 用户重新输入密码
	 */
	private String repeatNewPassword;

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

	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}
}
