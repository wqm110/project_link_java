package com.ruoyi.project.service.impl;


import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.service.IProjectCommonService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProjectCommonServiceImpl implements IProjectCommonService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查找某角色下的人员
     */
    @Override
    public List<Map<String, Object>> getUsersByRole(String roleKey) {
        List<SysUser> userList = sysUserMapper.selectUserListByRoleKey(roleKey);
        List<Map<String, Object>> dataList = new ArrayList<>();
        userList.forEach(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("nickName", user.getNickName());
            dataList.add(map);
        });
        return dataList;
    }
}
