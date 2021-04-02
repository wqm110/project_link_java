package com.ruoyi.project.service;

import java.util.List;
import java.util.Map;

public interface IProjectCommonService {

    /**
     * 查找某角色下的人员
     */
    List<Map<String, Object>> getUsersByRole(String roleId);
}
