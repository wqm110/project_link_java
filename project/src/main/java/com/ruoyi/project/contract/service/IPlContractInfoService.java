package com.ruoyi.project.contract.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.contract.domain.PlContractInfo;

/**
 * 合同信息Service接口
 *
 * @author ruoyi
 * @date 2021-04-03
 */
public interface IPlContractInfoService {
    /**
     * 查询合同信息
     *
     * @param contractId 合同信息ID
     * @return 合同信息
     */
    public PlContractInfo selectPlContractInfoById(Long contractId);

    /**
     * 查询合同信息列表
     *
     * @param plContractInfo 合同信息
     * @return 合同信息集合
     */
    public List<PlContractInfo> selectPlContractInfoList(PlContractInfo plContractInfo);

    /**
     * 新增合同信息
     *
     * @param plContractInfo 合同信息
     * @return 结果
     */
    public int insertPlContractInfo(PlContractInfo plContractInfo);

    /**
     * 修改合同信息
     *
     * @param plContractInfo 合同信息
     * @return 结果
     */
    public int updatePlContractInfo(PlContractInfo plContractInfo);

    /**
     * 批量删除合同信息
     *
     * @param contractIds 需要删除的合同信息ID
     * @return 结果
     */
    public int deletePlContractInfoByIds(Long[] contractIds);

    /**
     * 删除合同信息信息
     *
     * @param contractId 合同信息ID
     * @return 结果
     */
    public int deletePlContractInfoById(Long contractId);

    //合同首页汇总信息（文字_本年）
    Map<String, Object> getSummary(String year, String endYear);

    //合同首页汇总信息（图表-合同-年）
    Map<String, Object> getSummaryChartsContractYear(int valueOf, int valueOf1);

    //合同首页汇总信息（图表-合同-月）
    Map<String, String> getSummaryChartsContractMouth(int year);

    //合同首页汇总信息（图表-合同-季度）
    Map<String, String> getSummaryChartsContractQuerter(int year);

    //合同首页汇总信息（图表-实收-年）
    Map<String, Object> getSummaryChartsFeeCollectedYear(int syear, int year);

    //合同首页汇总信息（图表-实收-月）
    Map<String, Object> getSummaryChartsFeeCollectedMonth(int year);

    //合同首页汇总信息（图表-实收-季）
    Map<String, Object> getSummaryChartsFeeCollectedQuarter(int year);
}
