package com.lzh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.conf.common.Response;
import com.lzh.conf.common.ResponseStatus;
import com.lzh.mybatis.entity.MscUser;
import com.lzh.mybatis.mapper.MscUserMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
public class PermController {

	@Autowired
	private MscUserMapper mscUserMapper;
	
	@ApiOperation(value = "hello world示例1", notes = "hello world示例1")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public Response fist(){
		System.out.println("hello world !");
		return new Response(ResponseStatus.Success, "hello world !", true);
	}
	
	@ApiOperation(value = "hello world示例2", notes = "hello world示例2")
	@RequestMapping(value = "/secord", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('api/user:GET')")
	public Response secord(){
		System.out.println("hello world !");
		MscUser user = mscUserMapper.selectByPrimaryKey(new Long(1));
		return new Response(ResponseStatus.Success, user, true);
	}
}
