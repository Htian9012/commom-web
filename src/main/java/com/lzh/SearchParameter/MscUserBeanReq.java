package com.lzh.SearchParameter;

public class MscUserBeanReq {

	private long id;
	
	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户重新输入密码
	 */
	private String repeatPassword;
	
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 备注描述
	 */
	private String remark;

	/**
	 * 联系电话
	 */
	private String mobile;

	/**
	 * email
	 */
	private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
