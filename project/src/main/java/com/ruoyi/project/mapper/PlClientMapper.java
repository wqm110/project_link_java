package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.PlClient;

/**
 * 客户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface PlClientMapper 
{
    /**
     * 查询客户信息
     * 
     * @param clientId 客户信息ID
     * @return 客户信息
     */
    public PlClient selectPlClientById(String clientId);

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
     * 删除客户信息
     * 
     * @param clientId 客户信息ID
     * @return 结果
     */
    public int deletePlClientById(String clientId);

    /**
     * 批量删除客户信息
     * 
     * @param clientIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlClientByIds(String[] clientIds);
}
