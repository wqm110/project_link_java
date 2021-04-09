package com.ruoyi.project.contract.mapper;

import java.util.List;

import com.ruoyi.project.contract.domain.PlContractInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 合同信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-03
 */
public interface PlContractInfoMapper {
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
     * 删除合同信息
     *
     * @param contractId 合同信息ID
     * @return 结果
     */
    public int deletePlContractInfoById(Long contractId);

    /**
     * 批量删除合同信息
     *
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlContractInfoByIds(Long[] contractIds);

    List<PlContractInfo> countContractSum(@Param("s") String startYear, @Param("ed") String endYear);

    /**
     * 图表-合同-年（近五年）
     */
    List<PlContractInfo> getSummaryChartsContractYear(@Param("s") int startYear, @Param("ed") int endYear);

    /**
     *  图表-合同-月
     * */
    List<PlContractInfo> selectSummaryChartsContractMouth(@Param("y") int year);

    List<PlContractInfo> selectSummaryChartsContractQuarter(@Param("y") int year);
}
