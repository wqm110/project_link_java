package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.PlClientMapper;
import com.ruoyi.project.domain.PlClient;
import com.ruoyi.project.service.IPlClientService;

/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PlClientServiceImpl implements IPlClientService 
{
    @Autowired
    private PlClientMapper plClientMapper;

    /**
     * 查询客户信息
     * 
     * @param clientId 客户信息ID
     * @return 客户信息
     */
    @Override
    public PlClient selectPlClientById(String clientId)
    {
        return plClientMapper.selectPlClientById(clientId);
    }

    /**
     * 查询客户信息列表
     * 
     * @param plClient 客户信息
     * @return 客户信息
     */
    @Override
    public List<PlClient> selectPlClientList(PlClient plClient)
    {
        return plClientMapper.selectPlClientList(plClient);
    }

    /**
     * 新增客户信息
     * 
     * @param plClient 客户信息
     * @return 结果
     */
    @Override
    public int insertPlClient(PlClient plClient)
    {
        plClient.setCreateTime(DateUtils.getNowDate());
        return plClientMapper.insertPlClient(plClient);
    }

    /**
     * 修改客户信息
     * 
     * @param plClient 客户信息
     * @return 结果
     */
    @Override
    public int updatePlClient(PlClient plClient)
    {
        plClient.setUpdateTime(DateUtils.getNowDate());
        return plClientMapper.updatePlClient(plClient);
    }

    /**
     * 批量删除客户信息
     * 
     * @param clientIds 需要删除的客户信息ID
     * @return 结果
     */
    @Override
    public int deletePlClientByIds(String[] clientIds)
    {
        return plClientMapper.deletePlClientByIds(clientIds);
    }

    /**
     * 删除客户信息信息
     * 
     * @param clientId 客户信息ID
     * @return 结果
     */
    @Override
    public int deletePlClientById(String clientId)
    {
        return plClientMapper.deletePlClientById(clientId);
    }
}
