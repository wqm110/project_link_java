package com.ruoyi.project.contract.service;

import java.util.List;

import com.ruoyi.project.contract.domain.PlContractFeePlan;

/**
 * 合同收费计划Service接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface IPlContractFeePlanService {
    /**
     * 查询合同收费计划
     *
     * @param planId 合同收费计划ID
     * @return 合同收费计划
     */
    public PlContractFeePlan selectPlContractFeePlanById(Long planId);

    /**
     * 查询合同收费计划列表
     *
     * @param plContractFeePlan 合同收费计划
     * @return 合同收费计划集合
     */
    public List<PlContractFeePlan> selectPlContractFeePlanList(PlContractFeePlan plContractFeePlan);

    /**
     * 新增合同收费计划
     *
     * @param plContractFeePlan 合同收费计划
     * @return 结果
     */
    public int insertPlContractFeePlan(PlContractFeePlan plContractFeePlan);

    /**
     * 修改合同收费计划
     *
     * @param plContractFeePlan 合同收费计划
     * @return 结果
     */
    public int updatePlContractFeePlan(PlContractFeePlan plContractFeePlan);

    /**
     * 批量删除合同收费计划
     *
     * @param planIds 需要删除的合同收费计划ID
     * @return 结果
     */
    public int deletePlContractFeePlanByIds(String[] planIds);

    /**
     * 删除合同收费计划信息
     *
     * @param planId 合同收费计划ID
     * @return 结果
     */
    public int deletePlContractFeePlanById(String planId);

    /**
     * 根据合同id查询计划列表
     *
     * @param contractId 合同编号
     * @return 该合同下的计划列表
     */
    List<PlContractFeePlan> getContractPlansByFather(String contractId);
}
