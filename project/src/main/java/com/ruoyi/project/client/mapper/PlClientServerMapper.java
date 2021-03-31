package com.ruoyi.project.client.mapper;

import java.util.List;
import com.ruoyi.project.client.domain.PlClientServer;

/**
 * 客户-业务员服务关系Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface PlClientServerMapper 
{
    /**
     * 查询客户-业务员服务关系
     * 
     * @param clientId 客户-业务员服务关系ID
     * @return 客户-业务员服务关系
     */
    public PlClientServer selectPlClientServerById(String clientId);

    /**
     * 查询客户-业务员服务关系列表
     * 
     * @param plClientServer 客户-业务员服务关系
     * @return 客户-业务员服务关系集合
     */
    public List<PlClientServer> selectPlClientServerList(PlClientServer plClientServer);

    /**
     * 新增客户-业务员服务关系
     * 
     * @param plClientServer 客户-业务员服务关系
     * @return 结果
     */
    public int insertPlClientServer(PlClientServer plClientServer);

    /**
     * 修改客户-业务员服务关系
     * 
     * @param plClientServer 客户-业务员服务关系
     * @return 结果
     */
    public int updatePlClientServer(PlClientServer plClientServer);

    /**
     * 删除客户-业务员服务关系
     * 
     * @param clientId 客户-业务员服务关系ID
     * @return 结果
     */
    public int deletePlClientServerById(String clientId);

    /**
     * 批量删除客户-业务员服务关系
     * 
     * @param clientIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlClientServerByIds(String[] clientIds);

    void deletePlClientServerByUserId(Long userId);
}