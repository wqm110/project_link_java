package com.ruoyi.project.contract.mapper;

import java.util.List;

import com.ruoyi.project.contract.domain.PlContractFeePlan;
import com.ruoyi.project.contract.domain.PlContractInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 合同收费计划Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface PlContractFeePlanMapper {
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
     * 删除合同收费计划
     *
     * @param planId 合同收费计划ID
     * @return 结果
     */
    public int deletePlContractFeePlanById(String planId);

    /**
     * 批量删除合同收费计划
     *
     * @param planIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlContractFeePlanByIds(String[] planIds);

    /**
     * 根据合同id查询计划列表
     */
    List<PlContractFeePlan> getContractPlansByFatherId(String contractId);

    List<PlContractFeePlan> getcollected(@Param("s") String startYear, @Param("ed") String endYear);

    List<PlContractFeePlan> selectSummaryChartsFeeCollectedYear(@Param("s") int syear, @Param("ed") int year);

    List<PlContractFeePlan> getSummaryChartsFeeCollectedMonth(@Param("s") int year);

    List<PlContractFeePlan> getSummaryChartsFeeCollectedQuarter(@Param("s") int year);
}
