package com.lzh.SearchParameter;

import java.util.ArrayList;
import java.util.List;

import com.lzh.mybatis.entity.MscRole;

public class UserRolesBeanResp{
	
	private List<MscRole> mscAlreadHaveRoles = new ArrayList<>();
	
	private List<MscRole> mscNotHaveRoles = new ArrayList<>();

	public List<MscRole> getMscAlreadHaveRoles() {
		return mscAlreadHaveRoles;
	}

	public void setMscAlreadHaveRoles(List<MscRole> mscAlreadHaveRoles) {
		this.mscAlreadHaveRoles = mscAlreadHaveRoles;
	}

	public List<MscRole> getMscNotHaveRoles() {
		return mscNotHaveRoles;
	}

	public void setMscNotHaveRoles(List<MscRole> mscNotHaveRoles) {
		this.mscNotHaveRoles = mscNotHaveRoles;
	}
}
