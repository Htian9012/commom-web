package com.lzh.SearchParameter;

import java.util.ArrayList;
import java.util.List;

public class UserRolesBeanReq {
	
	private long userId;

	private List<String> deleteAlreadHaveRoles =new ArrayList<>();
	
	private List<String> addNotHaveRoles =new ArrayList<>();

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<String> getDeleteAlreadHaveRoles() {
		return deleteAlreadHaveRoles;
	}

	public void setDeleteAlreadHaveRoles(List<String> deleteAlreadHaveRoles) {
		this.deleteAlreadHaveRoles = deleteAlreadHaveRoles;
	}

	public List<String> getAddNotHaveRoles() {
		return addNotHaveRoles;
	}

	public void setAddNotHaveRoles(List<String> addNotHaveRoles) {
		this.addNotHaveRoles = addNotHaveRoles;
	}

	
	
}
