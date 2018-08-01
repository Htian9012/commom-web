package com.lzh.conf.common;

public enum ResponseStatus {

	
	Success(000, "Success"),
	
	Uniqueness(001,"Uniqueness"),//登录账号、手机号、邮箱唯一性报错
	
	Error(511, "Error"),//服务器错误
	
	AccessDenied(401, "AccessDenied"),//拒绝访问
	
	AuthenticationFailure(203, "AuthenticationFailure"),//认证失败
	;
	
	private final int value;

	private final String reasonPhrase;


	private ResponseStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * @param statusCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
	 */
	public static ResponseStatus valueOf(int statusCode) {
		for (ResponseStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

	

}
