package com.ruoyi.web.controller.tool;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.impl.SysUserServiceImpl;

public class GetUserInfo {

    public SysUserServiceImpl sysUserService = new SysUserServiceImpl();

    public SysUser getUserInfo(String userid) {
        return sysUserService.selectUserById(Long.getLong(userid));
    }
}
