package com.ceam.framework.auth.serivce;

import com.ceam.common.core.domain.entity.SysUser;
import com.ceam.sapi.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CeaM
 * @Description
 * @date 2020/10/9 15:50
 */
public class SysLoginService {

    @Autowired
    private ISysUserService userService;

    public SysUser login(String username, String password)
    {
        SysUser user = userService.selectUserByLoginName(username);
        return user;
    }

    public void logout(String loginName)
    {

    }
}
