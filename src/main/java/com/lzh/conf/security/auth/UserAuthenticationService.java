package com.lzh.conf.security.auth;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lzh.conf.security.model.UserContext;
import com.lzh.mybatis.entity.MscPerm;
import com.lzh.mybatis.entity.MscUser;
import com.lzh.mybatis.entity.MscUserExample;
import com.lzh.mybatis.mapper.MscPermMapper;
import com.lzh.mybatis.mapper.MscUserMapper;

/**
 * 用户认证实现
 * Created by htian
 */
@Component
public class UserAuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationService.class);
    
    @Autowired
    private MscUserMapper mscUserMapper;
    @Autowired
    private MscPermMapper mscPermMapper;
    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return MscUser
     */
    public MscUser getUser(String username) throws AuthenticationException {
        List<MscUser> users = new ArrayList<MscUser>();
        try {
            //user = dao.queryForSingleObject(MscUser.class, "select * from msc_user where username = ? and status = 1", username);
        	MscUserExample example =new MscUserExample();
            example.createCriteria().andStatusEqualTo(1).andUsernameEqualTo(username);
            users = mscUserMapper.selectByExample(example);
            System.out.println("登录用户:"+users.get(0).getRealName());
        } catch (Exception e) {
            log.error("查询用户异常", e);
        }
        if (users == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        return users.get(0);
    }

    /**
     * 查询用户权限信息
     *
     * @param username
     * @return
     */
    public UserContext getUseContext(String username) {
        MscUser user = getUser(username);
        List<MscPerm> permissions = new ArrayList<MscPerm>();
        // 根据用户查询所有权限
        try {
            /*StringBuffer permissionSql = new StringBuffer();
            permissionSql.append("select distinct p.* ")
                    .append("from msc_perm p ")
                    .append("inner join msc_role_perm rp on p.id = rp.perm_id ")
                    .append("inner join msc_user_role ur on ur.role_id = rp.role_id ")
                    .append("where ur.user_id = ? and p.status = 1 ")
                    .append("order by p.id");*/

            permissions = mscPermMapper.selectForSercurity(user.getId());
        } catch (Exception e) {
            log.error("查询权限信息异常", e);
        }

        List<GrantedAuthority> authorities = permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermValue())).collect(toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return userContext;
    }
}
