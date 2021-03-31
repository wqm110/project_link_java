package com.ruoyi.project.client.mapper;

import com.ruoyi.project.client.domain.PlClientServiceHis;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户跟进记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface PlClientServiceHisMapper 
{
    /**
     * 查询客户跟进记录
     * 
     * @param serviceHisId 客户跟进记录ID
     * @return 客户跟进记录
     */
    public PlClientServiceHis selectPlClientServiceHisById(Long serviceHisId);

    /**
     * 查询客户跟进记录列表
     * 
     * @param plClientServiceHis 客户跟进记录
     * @return 客户跟进记录集合
     */
    public List<PlClientServiceHis> selectPlClientServiceHisList(PlClientServiceHis plClientServiceHis);

    /**
     * 新增客户跟进记录
     * 
     * @param plClientServiceHis 客户跟进记录
     * @return 结果
     */
    public int insertPlClientServiceHis(PlClientServiceHis plClientServiceHis);

    /**
     * 修改客户跟进记录
     * 
     * @param plClientServiceHis 客户跟进记录
     * @return 结果
     */
    public int updatePlClientServiceHis(PlClientServiceHis plClientServiceHis);

    /**
     * 删除客户跟进记录
     * 
     * @param serviceHisId 客户跟进记录ID
     * @return 结果
     */
    public int deletePlClientServiceHisById(Long serviceHisId);

    /**
     * 批量删除客户跟进记录
     * 
     * @param serviceHisIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlClientServiceHisByIds(Long[] serviceHisIds);

    public List<PlClientServiceHis> selectPlClientServiceHisListByIds(ArrayList clientIds);
}