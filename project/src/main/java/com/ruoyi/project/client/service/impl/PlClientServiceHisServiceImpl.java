package com.ruoyi.project.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.client.mapper.PlClientServiceHisMapper;
import com.ruoyi.project.client.domain.PlClientServiceHis;
import com.ruoyi.project.client.service.IPlClientServiceHisService;

/**
 * 客户跟进记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PlClientServiceHisServiceImpl implements IPlClientServiceHisService {
    @Autowired
    private PlClientServiceHisMapper plClientServiceHisMapper;

    /**
     * 查询客户跟进记录
     *
     * @param serviceHisId 客户跟进记录ID
     * @return 客户跟进记录
     */
    @Override
    public PlClientServiceHis selectPlClientServiceHisById(Long serviceHisId) {
        return plClientServiceHisMapper.selectPlClientServiceHisById(serviceHisId);
    }

    /**
     * 查询客户跟进记录列表
     *
     * @param plClientServiceHis 客户跟进记录
     * @return 客户跟进记录
     */
    @Override
    public List<PlClientServiceHis> selectPlClientServiceHisList(PlClientServiceHis plClientServiceHis) {
        return plClientServiceHisMapper.selectPlClientServiceHisList(plClientServiceHis);
    }

    /**
     * 新增客户跟进记录
     *
     * @param plClientServiceHis 客户跟进记录
     * @return 结果
     */
    @Override
    public int insertPlClientServiceHis(PlClientServiceHis plClientServiceHis) {
        plClientServiceHis.setCreateTime(DateUtils.getNowDate());
        return plClientServiceHisMapper.insertPlClientServiceHis(plClientServiceHis);
    }

    /**
     * 修改客户跟进记录
     *
     * @param plClientServiceHis 客户跟进记录
     * @return 结果
     */
    @Override
    public int updatePlClientServiceHis(PlClientServiceHis plClientServiceHis) {
        plClientServiceHis.setUpdateTime(DateUtils.getNowDate());
        return plClientServiceHisMapper.updatePlClientServiceHis(plClientServiceHis);
    }

    /**
     * 批量删除客户跟进记录
     *
     * @param serviceHisIds 需要删除的客户跟进记录ID
     * @return 结果
     */
    @Override
    public int deletePlClientServiceHisByIds(Long[] serviceHisIds) {
        return plClientServiceHisMapper.deletePlClientServiceHisByIds(serviceHisIds);
    }

    /**
     * 删除客户跟进记录信息
     *
     * @param serviceHisId 客户跟进记录ID
     * @return 结果
     */
    @Override
    public int deletePlClientServiceHisById(Long serviceHisId) {
        return plClientServiceHisMapper.deletePlClientServiceHisById(serviceHisId);
    }

    @Override
    public List<PlClientServiceHis> selectPlClientServiceHisListByIds(ArrayList clientIds) {

        return plClientServiceHisMapper.selectPlClientServiceHisListByIds(clientIds);
    }
}