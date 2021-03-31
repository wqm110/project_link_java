package com.ruoyi.project.client.controller;

import com.ruoyi.project.client.domain.PlClientServer;
import com.ruoyi.project.client.service.IPlClientServerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class GetClients {
    @Autowired
    private static IPlClientServerService iPlClientServerService;

    /**
     * 通过中间表查询到业务员所管理的项目的客户编号
     */
    public static ArrayList<Long> getClientsByUserid(Long userId) {
        PlClientServer plClientServer = new PlClientServer();
        if ((null != userId)) {
            return null;
        }
        plClientServer.setUserId(userId);
        List<PlClientServer> plClientServers = iPlClientServerService.selectPlClientServerList(plClientServer);
        ArrayList<Long> ids = new ArrayList<>();
        plClientServers.forEach(x -> {
            ids.add(x.getClientId());
        });
        return ids;
    }
}
