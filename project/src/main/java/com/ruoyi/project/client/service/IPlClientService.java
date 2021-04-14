package com.ruoyi.project.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.client.domain.PlClient;

/**
 * 客户信息Service接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface IPlClientService 
{
    /**
     * 查询客户信息
     * 
     * @param clientId 客户信息ID
     * @return 客户信息
     */
    public PlClient selectPlClientById(Long clientId);

    /**
     * 查询客户信息列表
     * 
     * @param plClient 客户信息
     * @return 客户信息集合
     */
    public List<PlClient> selectPlClientList(PlClient plClient);

    /**
     * 新增客户信息
     * 
     * @param plClient 客户信息
     * @return 结果
     */
    public int insertPlClient(PlClient plClient);

    /**
     * 修改客户信息
     * 
     * @param plClient 客户信息
     * @return 结果
     */
    public int updatePlClient(PlClient plClient);

    /**
     * 批量删除客户信息
     * 
     * @param clientIds 需要删除的客户信息ID
     * @return 结果
     */
    public int deletePlClientByIds(Long[] clientIds);

    /**
     * 删除客户信息信息
     * 
     * @param clientId 客户信息ID
     * @return 结果
     */
    public int deletePlClientById(Long clientId);

    List<PlClient> selectPlClientListByIds(ArrayList<String> clientsUserIds);

    Map<String, Object> getSummary(Long userId);
}
