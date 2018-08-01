package com.lzh.controller;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzh.SearchParameter.MscUserBeanReq;
import com.lzh.SearchParameter.MscUserPassBeanReq;
import com.lzh.SearchParameter.RolePermsBeanReq;
import com.lzh.SearchParameter.RolePermsBeanResp;
import com.lzh.SearchParameter.UserRolesBeanReq;
import com.lzh.SearchParameter.UserRolesBeanResp;
import com.lzh.conf.security.auth.jwt.JwtAuthenticationToken;
import com.lzh.conf.security.model.UserContext;
import com.lzh.conf.security.utils.AuthUtils;
import com.lzh.mybatis.entity.MscPerm;
import com.lzh.mybatis.entity.MscPermExample;
import com.lzh.mybatis.entity.MscRole;
import com.lzh.mybatis.entity.MscRoleExample;
import com.lzh.mybatis.entity.MscRolePerm;
import com.lzh.mybatis.entity.MscRolePermExample;
import com.lzh.mybatis.entity.MscUser;
import com.lzh.mybatis.entity.MscUserExample;
import com.lzh.mybatis.entity.MscUserRole;
import com.lzh.mybatis.entity.MscUserRoleExample;
import com.lzh.mybatis.mapper.MscPermMapper;
import com.lzh.mybatis.mapper.MscRoleMapper;
import com.lzh.mybatis.mapper.MscRolePermMapper;
import com.lzh.mybatis.mapper.MscUserMapper;
import com.lzh.mybatis.mapper.MscUserRoleMapper;
import com.lzh.util.KeyFactory.IdWorkerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "adminManage", description = "用户管理模块接口")
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private MscUserMapper mscUserMapper;
	@Autowired
	private MscPermMapper mscPermMapper;
	@Autowired
	private MscRoleMapper mscRoleMapper;
	@Autowired
	private MscUserRoleMapper mscUserRoleMapper;
	@Autowired
	private MscRolePermMapper mscRolePermMapper;
	/**
	 * 用户查询自己的详细信息
	 */
	@ApiOperation(value = "用户管理->用户查询自己详细信息", notes = "")
	@RequestMapping(value = "/mscUser/load", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/mscUser/load:GET')")
	public MscUser loadUser(JwtAuthenticationToken token) {
		UserContext userContext = AuthUtils.getCurrentUser(token);
		try {
			MscUserExample example = new MscUserExample();
			example.createCriteria().andStatusEqualTo(1).andUsernameEqualTo(userContext.getUsername());
			MscUser mscUser = mscUserMapper.selectByExample(example).get(0);
			mscUser.setPassword(null);
			return mscUser;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	/**
	 * 用户修改个人资料
	 *
	 * @param mscUser
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用户管理->修改个人资料", notes = "用户表(msc_user)：修改登录用户的个人资料")
	@RequestMapping(value = "/mscUser/profile", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscUser/profile:PUT')")
	public boolean updateProfile(@RequestBody MscUser mscUser, JwtAuthenticationToken token)
			throws Exception {
		UserContext userContext = AuthUtils.getCurrentUser(token);
		try {
			MscUserExample example = new MscUserExample();
			example.createCriteria().andStatusEqualTo(1).andUsernameEqualTo(userContext.getUsername());
			MscUser user = mscUserMapper.selectByExample(example).get(0);
			//更新用户
			user.setEmail(mscUser.getEmail());
			user.setMobile(mscUser.getMobile());
			user.setRemark(mscUser.getRemark());
			user.setRealName(mscUser.getRealName());
			user.setModifyDate(new Date());
			mscUserMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			return false;
		}
		// 待定，建议统一返回的格式
		return true;
	}

	/**
	 * 用户修改个人密码
	 *
	 * @param mscUser
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用户管理->修改个人密码", notes = "用户表(msc_user)：修改个人密码")
	@RequestMapping(value = "/mscUser/reset", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscUser/reset:PUT')")
	public boolean resetPassword(@RequestBody MscUserPassBeanReq mscUser, JwtAuthenticationToken token)
			throws Exception {
		try {
			if (!mscUser.getNewPassword().equals(mscUser.getRepeatNewPassword()))
				return false;
			UserContext userContext = AuthUtils.getCurrentUser(token);
			MscUserExample example = new MscUserExample();
			example.createCriteria().andStatusEqualTo(1).andUsernameEqualTo(userContext.getUsername());
			MscUser user = mscUserMapper.selectByExample(example).get(0);
			
			if (!encoder.matches(mscUser.getOldPassword(), user.getPassword()))
				return false;
			user.setPassword(encoder.encode(mscUser.getNewPassword()));
			user.setModifyDate(new Date());
			mscUserMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 更新用户信息(管理员)
	 */
	@ApiOperation(value = "用户管理->更新用户信息(管理员)", notes = "用户表(msc_user)：更新用户信息(管理员)")
	@RequestMapping(value = "/mscUser/modify", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscUser/modify:PUT')")
	public boolean updateUser(@RequestBody MscUserBeanReq mscUserBeanReq) throws Exception {
		try {
			if (!mscUserBeanReq.getPassword().equals(mscUserBeanReq.getRepeatPassword()))
				return false;
			MscUser user = mscUserMapper.selectByPrimaryKey(mscUserBeanReq.getId());
			// user.setStatus(mscUser.getStatus());
			user.setEmail(mscUserBeanReq.getEmail());
			user.setMobile(mscUserBeanReq.getMobile());
			user.setPassword(encoder.encode(mscUserBeanReq.getPassword()));
			user.setRealName(mscUserBeanReq.getRealName());
			user.setRemark(mscUserBeanReq.getRemark());
			user.setModifyDate(new Date());
			mscUserMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 查询所有的用户列表 (分页)
	 */
	@ApiOperation(value = "用户管理->查询用户表(msc_user)", notes = "用户表(msc_user)：查询所有用户记录")
	@RequestMapping(value = "/mscUsers", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/mscUsers:GET')")
	public PageInfo<MscUser> getMscUsers(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum)
			throws Exception {
		PageHelper.startPage(pageNum,pageSize);
		List<MscUser> list = mscUserMapper.selectByExample(new MscUserExample());
		return new  PageInfo<MscUser>(list);
	}

	/**
	 * 修改某用户(被锁定用户不能登录平台)
	 */
	@ApiOperation(value = "用户管理->修改用户状态(需要塞id和status,其他的传空)", notes = "用户表(msc_user)：根据id修改用户状态 ,把status改为-1(状态：1正常 -1标记删除 0被锁定)")
	@RequestMapping(value = "/mscUser", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscUser:PUT')")
	public boolean updateUserById(@RequestBody MscUser mscUser) throws Exception {
		try {
			MscUser user = mscUserMapper.selectByPrimaryKey(mscUser.getId());
			user.setStatus(mscUser.getStatus());
			user.setModifyDate(new Date());
			mscUserMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 查询用户详细信息
	 */
	@ApiOperation(value = "用户管理->查询用户详细信息", notes = "用户表(msc_user)：根据id查询用户详细信息")
	@RequestMapping(value = "/mscUser/{id}", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/mscUser:GET')")
	public MscUser loadUserById(@PathVariable("id") String id) throws Exception {
		MscUser mscUser = mscUserMapper.selectByPrimaryKey(Long.parseLong(id));
		return mscUser;
	}

	/**
	 * 新增用户
	 */
	@ApiOperation(value = "用户管理->新增用户", notes = "用户表(msc_user)：新增用户")
	@RequestMapping(value = "/mscUser", method = { RequestMethod.POST })
	//@PreAuthorize("hasAuthority('admin/mscUser:POST')")
	public int createUserById(@RequestBody MscUser mscUser) throws Exception {
		mscUser.setId(IdWorkerFactory.getIdWorker().nextId());
		mscUser.setPassword(encoder.encode(mscUser.getPassword()));
		mscUser.setStatus(1);
		mscUser.setCreateDate(new Date());
		return mscUserMapper.insertSelective(mscUser);
	}

	/**
	 * 查询用户具有角色和未具有角色(索引:用户主键)
	 */
	@ApiOperation(value = "用户管理->查询用户具有角色和未具有角色", notes = "角色表(msc_user)：根据id查询用户具有角色和未具有角色")
	@RequestMapping(value = "/userRoles/{id}", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/userRoles:GET')")
	public UserRolesBeanResp loadUserRolesById(@PathVariable("id") String id) throws Exception {
		// 具有的角色
		List<MscRole> mscAlreadHaveRoles = mscRoleMapper.getAlreadHaveRolesByUserId(Long.parseLong(id));
		// 未具有角色
		List<MscRole> mscNotHaveRoles = mscRoleMapper.getNotHaveRoleByUserId(Long.parseLong(id));
		UserRolesBeanResp resp = new UserRolesBeanResp();
		resp.setMscAlreadHaveRoles(mscAlreadHaveRoles);
		resp.setMscNotHaveRoles(mscNotHaveRoles);
		return resp;
	}

	/**
	 * (授予或取消)更新用户角色(索引:用户主键)
	 */
	@ApiOperation(value = "用户管理->授予或取消用户角色", notes = "用户_角色表(msc_user_role)：根据id授予或取消用户角色")
	@RequestMapping(value = "/userRoles", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/userRoles:PUT')")
	public boolean updateUserRolesById(@RequestBody UserRolesBeanReq reqBean) throws Exception {
		try {
			// 新增用户未具有角色
			for (int i = 0; i < reqBean.getAddNotHaveRoles().size(); i++) {
				if (reqBean.getAddNotHaveRoles().get(i).equals(""))
					break;
				MscUserRole record = new MscUserRole();
				record.setCreateDate(new Date());
				record.setId(IdWorkerFactory.getIdWorker().nextId());
				record.setUserId(reqBean.getUserId());
				record.setStatus(1);
				record.setRoleId(new Long(reqBean.getAddNotHaveRoles().get(i)));
				mscUserRoleMapper.insertSelective(record);
			}
			// 删除用户原具有,但现在取消的角色(把状态status状态 修改为-1, 1正常0冻结-1标记删除)
			for (int i = 0; i < reqBean.getDeleteAlreadHaveRoles().size(); i++) {
				if (reqBean.getDeleteAlreadHaveRoles().get(i).equals(""))
					break;
				MscUserRoleExample example = new MscUserRoleExample();
				example.createCriteria().andUserIdEqualTo(reqBean.getUserId()).
				andRoleIdEqualTo(Long.parseLong(reqBean.getDeleteAlreadHaveRoles().get(i))).andStatusEqualTo(1);
				
				MscUserRole record = new MscUserRole();
				record.setStatus(-1);
				mscUserRoleMapper.updateByExampleSelective(record, example);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 查询所有角色列表
	 */
	@ApiOperation(value = "角色管理->查询角色列表(msc_role)", notes = "角色表(msc_role)：查询所有记录")
	@RequestMapping(value = "/mscRoles", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/mscRoles:GET')")
	public PageInfo<MscRole> getMscRoles(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum)
			throws Exception {
		PageHelper.startPage(pageNum,pageSize);
		List<MscRole> list = mscRoleMapper.selectByExample(new MscRoleExample());
		return new PageInfo<MscRole>(list);
	}

	/**
	 * 新增角色
	 */
	@ApiOperation(value = "角色管理->新增角色", notes = "角色表(msc_role)：新增记录")
	@RequestMapping(value = "/mscRole", method = { RequestMethod.POST })
	//@PreAuthorize("hasAuthority('admin/mscRole:POST')")
	public int addMscRole(@RequestBody MscRole mscRole) throws Exception {
		mscRole.setId(IdWorkerFactory.getIdWorker().nextId());
		mscRole.setCreateDate(new Date());
		return mscRoleMapper.insertSelective(mscRole);
	}

	/**
	 * 更新角色信息
	 */
	@ApiOperation(value = "角色管理->更新角色信息(msc_role)", notes = "角色表(msc_role)：更新")
	@RequestMapping(value = "/mscRole", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscRole:PUT')")
	public boolean updateMscRole(@RequestBody MscRole mscRole) throws Exception {
		try {
			MscRole record = mscRoleMapper.selectByPrimaryKey(mscRole.getId());
			record.setModifyDate(new Date());
			record.setRoleDesc(mscRole.getRoleDesc());
			record.setRoleName(mscRole.getRoleName());
			record.setStatus(mscRole.getStatus());
			mscRoleMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 查询某角色具有权限和未具有权限(索引:角色主键)
	 */
	@ApiOperation(value = "角色管理->查询角色具有权限和未具有权限", notes = "权限表(msc_perms)：根据id查询角色具有权限和未具有权限")
	@RequestMapping(value = "/rolePerms/{id}", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/rolePerms:GET')")
	public RolePermsBeanResp loadRolePermById(@PathVariable("id") String id) throws Exception {
		// 具有的权限
		List<MscPerm> mscAlreadHavePerms = mscPermMapper.getAlreadHavePermsByRoleId(Long.parseLong(id));
		// 未具有权限
		List<MscPerm> mscNotHavePerms = mscPermMapper.getNotHavePermsByRoleId(Long.parseLong(id));
		RolePermsBeanResp resp = new RolePermsBeanResp();
		resp.setMscAlreadHavePerms(mscAlreadHavePerms);
		resp.setMscNotHavePerms(mscNotHavePerms);
		return resp;
	}

	/**
	 * (添加或取消)更新某角色权限(索引:角色主键)
	 */
	@ApiOperation(value = "角色管理->授予或取消角色的权限", notes = "角色_权限表(msc_role_perm)：根据id授予或取消角色的权限")
	@RequestMapping(value = "/rolePerms", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/rolePerms:PUT')")
	public boolean updateRolePermsById(@RequestBody RolePermsBeanReq reqBean) throws Exception {
		try {
			// 新增角色未具有权限
			for (int i = 0; i < reqBean.getAddNotHavePerms().size(); i++) {
				if (reqBean.getAddNotHavePerms().get(i).equals(""))
					break;
				MscRolePerm record = new MscRolePerm();
				record.setCreateDate(new Date());
				record.setId(IdWorkerFactory.getIdWorker().nextId());
				record.setRoleId(reqBean.getRoleId());
				record.setPermId(new Long(reqBean.getAddNotHavePerms().get(i)));
				record.setStatus(1);
				mscRolePermMapper.insertSelective(record);
			}
			// 删除角色原来具有但现在取消的权限
			for (int i = 0; i < reqBean.getDeleteAlreadHavePerms().size(); i++) {
				if (reqBean.getDeleteAlreadHavePerms().get(i).equals(""))
					break;
				MscRolePermExample example = new MscRolePermExample();
				example.createCriteria().andRoleIdEqualTo(reqBean.getRoleId())
				.andPermIdEqualTo(Long.parseLong( reqBean.getDeleteAlreadHavePerms().get(i))).andStatusEqualTo(1);
				
				MscRolePerm record = new MscRolePerm();
				record.setStatus(-1);
				
				mscRolePermMapper.updateByExample(record, example);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 查询所有权限列表
	 */
	@ApiOperation(value = "权限管理->查询权限列表(msc_perm)", notes = "权限表(msc_perm)：查询所有记录")
	@RequestMapping(value = "/mscPerms", method = { RequestMethod.GET })
	//@PreAuthorize("hasAuthority('admin/mscPerms:GET')")
	public PageInfo<MscPerm> getMscPerms(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum)
			throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		List<MscPerm> list = mscPermMapper.selectByExample(new MscPermExample());
		return new PageInfo<MscPerm>(list);
	}

	/**
	 * 更新权限信息
	 */
	@ApiOperation(value = "权限管理->更新权限信息(msc_perm)", notes = "权限表(msc_perm)：查询所有记录")
	@RequestMapping(value = "/mscPerm", method = { RequestMethod.PUT })
	//@PreAuthorize("hasAuthority('admin/mscPerm:PUT')")
	public int updateMscPerm(@RequestBody MscPerm mscPerm) throws Exception {
		MscPerm record = mscPermMapper.selectByPrimaryKey(mscPerm.getId());
		record.setModifyDate(new Date());
		record.setPermDesc(mscPerm.getPermDesc());
		record.setPermLevel(mscPerm.getPermLevel());
		// record.setPermName(mscPerm.getPermName());
		record.setPermOrder(mscPerm.getPermOrder());
		// record.setPermValue(mscPerm.getPermValue());//注解值不能随便修改,如果修改了,需要相应修改代码的注解值
		record.setStatus(mscPerm.getStatus());
		return mscPermMapper.updateByPrimaryKeySelective(record);
	}

}
