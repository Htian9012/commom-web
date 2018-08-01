package com.lzh.SearchParameter;

import java.util.ArrayList;
import java.util.List;

import com.lzh.mybatis.entity.MscPerm;

public class RolePermsBeanResp{
	
	private List<MscPerm> mscAlreadHavePerms = new ArrayList<>();
	
	private List<MscPerm> mscNotHavePerms = new ArrayList<>();

	public List<MscPerm> getMscAlreadHavePerms() {
		return mscAlreadHavePerms;
	}

	public void setMscAlreadHavePerms(List<MscPerm> mscAlreadHavePerms) {
		this.mscAlreadHavePerms = mscAlreadHavePerms;
	}

	public List<MscPerm> getMscNotHavePerms() {
		return mscNotHavePerms;
	}

	public void setMscNotHavePerms(List<MscPerm> mscNotHavePerms) {
		this.mscNotHavePerms = mscNotHavePerms;
	}
}
