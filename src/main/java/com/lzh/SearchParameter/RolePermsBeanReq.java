package com.lzh.SearchParameter;

import java.util.ArrayList;
import java.util.List;

public class RolePermsBeanReq {
	
	private long roleId;

	private List<String> deleteAlreadHavePerms =new ArrayList<>();
	
	private List<String> addNotHavePerms =new ArrayList<>();

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public List<String> getDeleteAlreadHavePerms() {
		return deleteAlreadHavePerms;
	}

	public void setDeleteAlreadHavePerms(List<String> deleteAlreadHavePerms) {
		this.deleteAlreadHavePerms = deleteAlreadHavePerms;
	}

	public List<String> getAddNotHavePerms() {
		return addNotHavePerms;
	}

	public void setAddNotHavePerms(List<String> addNotHavePerms) {
		this.addNotHavePerms = addNotHavePerms;
	}
	
}
