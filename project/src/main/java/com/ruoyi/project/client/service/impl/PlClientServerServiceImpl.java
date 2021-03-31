package com.ruoyi.project.client.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.client.domain.PlClientServer;
import com.ruoyi.project.client.mapper.PlClientServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.client.service.IPlClientServerService;

/**
 * 客户-业务员服务关系Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PlClientServerServiceImpl implements IPlClientServerService {
    @Autowired
    private PlClientServerMapper plClientServerMapper;

    /**
     * 查询客户-业务员服务关系
     *
     * @param clientId 客户-业务员服务关系ID
     * @return 客户-业务员服务关系
     */
    @Override
    public PlClientServer selectPlClientServerById(String clientId) {
        return plClientServerMapper.selectPlClientServerById(clientId);
    }

    /**
     * 查询客户-业务员服务关系列表
     *
     * @param plClientServer 客户-业务员服务关系
     * @return 客户-业务员服务关系
     */
    @Override
    public List<PlClientServer> selectPlClientServerList(PlClientServer plClientServer) {
        return plClientServerMapper.selectPlClientServerList(plClientServer);
    }

    /**
     * 新增客户-业务员服务关系
     *
     * @param plClientServer 客户-业务员服务关系
     * @return 结果
     */
    @Override
    public int insertPlClientServer(PlClientServer plClientServer) {
        plClientServer.setCreateTime(DateUtils.getNowDate());
        return plClientServerMapper.insertPlClientServer(plClientServer);
    }

    /**
     * 修改客户-业务员服务关系
     *
     * @param plClientServer 客户-业务员服务关系
     * @return 结果
     */
    @Override
    public int updatePlClientServer(PlClientServer plClientServer) {
        plClientServer.setUpdateTime(DateUtils.getNowDate());
        return plClientServerMapper.updatePlClientServer(plClientServer);
    }

    /**
     * 批量删除客户-业务员服务关系
     *
     * @param clientIds 需要删除的客户-业务员服务关系ID
     * @return 结果
     */
    @Override
    public int deletePlClientServerByIds(String[] clientIds) {
        return plClientServerMapper.deletePlClientServerByIds(clientIds);
    }

    /**
     * 删除客户-业务员服务关系信息
     *
     * @param clientId 客户-业务员服务关系ID
     * @return 结果
     */
    @Override
    public int deletePlClientServerById(String clientId) {
        return plClientServerMapper.deletePlClientServerById(clientId);
    }

    @Override
    public void deletePlClientServerByUserId(Long userId) {
        plClientServerMapper.deletePlClientServerByUserId(userId);
    }
}