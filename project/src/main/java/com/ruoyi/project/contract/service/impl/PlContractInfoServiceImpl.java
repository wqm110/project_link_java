package com.ruoyi.project.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.contract.mapper.PlContractInfoMapper;
import com.ruoyi.project.contract.domain.PlContractInfo;
import com.ruoyi.project.contract.service.IPlContractInfoService;

/**
 * 合同信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-03
 */
@Service
public class PlContractInfoServiceImpl implements IPlContractInfoService 
{
    @Autowired
    private PlContractInfoMapper plContractInfoMapper;

    /**
     * 查询合同信息
     * 
     * @param contractId 合同信息ID
     * @return 合同信息
     */
    @Override
    public PlContractInfo selectPlContractInfoById(Long contractId)
    {
        return plContractInfoMapper.selectPlContractInfoById(contractId);
    }

    /**
     * 查询合同信息列表
     * 
     * @param plContractInfo 合同信息
     * @return 合同信息
     */
    @Override
    public List<PlContractInfo> selectPlContractInfoList(PlContractInfo plContractInfo)
    {
        return plContractInfoMapper.selectPlContractInfoList(plContractInfo);
    }

    /**
     * 新增合同信息
     * 
     * @param plContractInfo 合同信息
     * @return 结果
     */
    @Override
    public int insertPlContractInfo(PlContractInfo plContractInfo)
    {
        plContractInfo.setCreateTime(DateUtils.getNowDate());
        return plContractInfoMapper.insertPlContractInfo(plContractInfo);
    }

    /**
     * 修改合同信息
     * 
     * @param plContractInfo 合同信息
     * @return 结果
     */
    @Override
    public int updatePlContractInfo(PlContractInfo plContractInfo)
    {
        plContractInfo.setUpdateTime(DateUtils.getNowDate());
        return plContractInfoMapper.updatePlContractInfo(plContractInfo);
    }

    /**
     * 批量删除合同信息
     * 
     * @param contractIds 需要删除的合同信息ID
     * @return 结果
     */
    @Override
    public int deletePlContractInfoByIds(Long[] contractIds)
    {
        return plContractInfoMapper.deletePlContractInfoByIds(contractIds);
    }

    /**
     * 删除合同信息信息
     * 
     * @param contractId 合同信息ID
     * @return 结果
     */
    @Override
    public int deletePlContractInfoById(Long contractId)
    {
        return plContractInfoMapper.deletePlContractInfoById(contractId);
    }
}
