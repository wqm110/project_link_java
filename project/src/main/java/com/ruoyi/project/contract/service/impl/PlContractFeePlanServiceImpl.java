package com.ruoyi.project.contract.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.contract.mapper.PlContractFeePlanMapper;
import com.ruoyi.project.contract.domain.PlContractFeePlan;
import com.ruoyi.project.contract.service.IPlContractFeePlanService;

/**
 * 合同收费计划Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class PlContractFeePlanServiceImpl implements IPlContractFeePlanService {
    @Autowired
    private PlContractFeePlanMapper plContractFeePlanMapper;

    /**
     * 查询合同收费计划
     *
     * @param planId 合同收费计划ID
     * @return 合同收费计划
     */
    @Override
    public PlContractFeePlan selectPlContractFeePlanById(Long planId) {
        return plContractFeePlanMapper.selectPlContractFeePlanById(planId);
    }

    /**
     * 查询合同收费计划列表
     *
     * @param plContractFeePlan 合同收费计划
     * @return 合同收费计划
     */
    @Override
    public List<PlContractFeePlan> selectPlContractFeePlanList(PlContractFeePlan plContractFeePlan) {
        return plContractFeePlanMapper.selectPlContractFeePlanList(plContractFeePlan);
    }

    /**
     * 新增合同收费计划
     *
     * @param plContractFeePlan 合同收费计划
     * @return 结果
     */
    @Override
    public int insertPlContractFeePlan(PlContractFeePlan plContractFeePlan) {
        plContractFeePlan.setCreateTime(DateUtils.getNowDate());
        return plContractFeePlanMapper.insertPlContractFeePlan(plContractFeePlan);
    }

    /**
     * 修改合同收费计划
     *
     * @param plContractFeePlan 合同收费计划
     * @return 结果
     */
    @Override
    public int updatePlContractFeePlan(PlContractFeePlan plContractFeePlan) {
        plContractFeePlan.setUpdateTime(DateUtils.getNowDate());
        return plContractFeePlanMapper.updatePlContractFeePlan(plContractFeePlan);
    }

    /**
     * 批量删除合同收费计划
     *
     * @param planIds 需要删除的合同收费计划ID
     * @return 结果
     */
    @Override
    public int deletePlContractFeePlanByIds(String[] planIds) {
        return plContractFeePlanMapper.deletePlContractFeePlanByIds(planIds);
    }

    /**
     * 删除合同收费计划信息
     *
     * @param planId 合同收费计划ID
     * @return 结果
     */
    @Override
    public int deletePlContractFeePlanById(String planId) {
        return plContractFeePlanMapper.deletePlContractFeePlanById(planId);
    }
    //
    /**
     * 根据合同id查询计划列表
     */
    @Override
    public List<PlContractFeePlan> getContractPlansByFather(String contractId) {
        return plContractFeePlanMapper.getContractPlansByFatherId(contractId);
    }
}
