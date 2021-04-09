package com.ruoyi.project.contract.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.contract.domain.PlContractFeePlan;
import com.ruoyi.project.contract.domain.PlContractInfo;
import com.ruoyi.project.contract.mapper.PlContractFeePlanMapper;
import com.ruoyi.project.contract.mapper.PlContractInfoMapper;
import com.ruoyi.project.contract.service.IPlContractInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 合同信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-03
 */
@Service
public class PlContractInfoServiceImpl implements IPlContractInfoService {
    @Autowired
    private PlContractInfoMapper plContractInfoMapper;
    @Autowired
    private PlContractFeePlanMapper plContractFeePlanMapper;

    /**
     * 查询合同信息
     *
     * @param contractId 合同信息ID
     * @return 合同信息
     */
    @Override
    public PlContractInfo selectPlContractInfoById(Long contractId) {
        return plContractInfoMapper.selectPlContractInfoById(contractId);
    }

    /**
     * 查询合同信息列表
     *
     * @param plContractInfo 合同信息
     * @return 合同信息
     */
    @Override
    public List<PlContractInfo> selectPlContractInfoList(PlContractInfo plContractInfo) {
        return plContractInfoMapper.selectPlContractInfoList(plContractInfo);
    }

    /**
     * 新增合同信息
     *
     * @param plContractInfo 合同信息
     * @return 结果
     */
    @Override
    public int insertPlContractInfo(PlContractInfo plContractInfo) {
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
    public int updatePlContractInfo(PlContractInfo plContractInfo) {
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
    public int deletePlContractInfoByIds(Long[] contractIds) {
        return plContractInfoMapper.deletePlContractInfoByIds(contractIds);
    }

    /**
     * 删除合同信息信息
     *
     * @param contractId 合同信息ID
     * @return 结果
     */
    @Override
    public int deletePlContractInfoById(Long contractId) {
        return plContractInfoMapper.deletePlContractInfoById(contractId);
    }

    @Override
    public Map<String, Object> getSummary(String startYear, String endYear) {
        /**
         * @Title: getSummary
         * <p>Description:  合同首页汇总信息（文字_本年） </p>
         * @author: wqm
         * @Param: startYear 开始年份
         * @Param: endYear 结束年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.Object>
         */
//        plContractInfoMapper
        /**
         * 统计合同总数
         *
         */
        Map<String, Object> summary = new HashMap<String, Object>();
        List<PlContractInfo> contractInfos = plContractInfoMapper.countContractSum(startYear, endYear);
        Long contractSum = contractInfos.stream().filter(x -> x.getContractStatus().equals("1")).count();
        //统计已完成 contract_status = 2
        Long contractFinished = contractInfos.stream().filter(x -> x.getContractStatus().equals("2")).count();
        //统计进行中
        Long contractSumRunning = contractInfos.stream().filter(x -> x.getContractStatus().equals("1") & (!x.getContractStatus().equals("2"))).count();
        //统计合同金额（元）
        double sumFee = contractInfos.stream().mapToDouble(x -> x.getContractAmount().doubleValue()).sum();
        /*
         * 回款情况
         */

        List<PlContractFeePlan> contractInfos_c = plContractFeePlanMapper.getcollected(startYear, endYear);
        List<PlContractFeePlan> collect = new ArrayList<>();
        Double colectedFee = 0.0;
        Double unClectedFee = 0.0;
        try {
            collect = contractInfos_c.stream().filter(x -> x.getCompleteStatus().equals("1")).collect(Collectors.toList());
            //统计已收总额
            colectedFee = contractInfos_c.stream().filter(x -> x.getCompleteStatus().equals("1")).mapToDouble(x -> x.getPlanFee().doubleValue()).sum();
            //统计未收总额
            unClectedFee = contractInfos_c.stream().filter(x -> x.getCompleteStatus().equals("0")).mapToDouble(x -> x.getPlanFee().doubleValue()).sum();
            //统计
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //统计

        summary.put("info", "contractSum：合同数量\ncontractFinished:已完成合同数量\ncontractSumRunning进行中的合同\nsumFee:合同金额\n已收金额：colectedFee\n未收金额：unCllectedFee");
        summary.put("contractSum", contractSum);
        summary.put("contractFinished", contractFinished);
        summary.put("contractSumRunning", contractSumRunning);
        summary.put("sumFee", sumFee);
        summary.put("colectedFee", colectedFee);
        summary.put("unClectedFee", unClectedFee);
        return summary;
    }


    @Override
    public Map<String, Object> getSummaryChartsContractYear(int startYear, int endYear) {
        List<PlContractInfo> list = plContractInfoMapper.getSummaryChartsContractYear(startYear, endYear);
        Map<String, Object> map = new HashMap<>();
        /**
         * @Title: getSummaryChartsContractYear
         * <p>Description:合同首页汇总信息（图表-合同-年） </p>
         * @author: wqm
         * @Param: startYear 开始年份-
         * @Param: endYear 结束年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.Object>
         *
         * 先默认 0
         * 从查询的集合中遍历取值
         *  如果和目前年份相同，填充
         */
        for (int i = startYear; i <= endYear; i++) {
            map.put(String.valueOf(i), 0);
            int finalI = i;
            list.forEach(x -> {
                if (x.getSy().equals(String.valueOf(finalI))) {
                    map.put(String.valueOf(finalI), x.getContractAmount());
                }
            });
        }
        return map;
    }

    @Override
    public Map<String, String> getSummaryChartsContractMouth(int year) {
        /**
         * @Title: getSummaryChartsContractMouth
         * <p>Description:合同首页汇总信息（图表-合同-月） </p>
         * @author: wqm
         * @Param: year 当前年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.String>
         */
        Map<String, String> map = new HashMap<>();
        List<PlContractInfo> list = plContractInfoMapper.selectSummaryChartsContractMouth(year);
        for (int i = 1; i < 13; i++) {
            map.put(String.valueOf(i), "0");
            int finalI = i;
            list.forEach(x -> {
                if (x.getSm().equals(String.valueOf(finalI))) {
                    map.put(String.valueOf(finalI), x.getContractAmount().toString());
                }
            });
        }
        return map;
    }

    @Override
    public Map<String, String> getSummaryChartsContractQuerter(int year) {
        /**
         * @Title: getSummaryChartsContractQuerter
         * <p>Description:合同首页汇总信息（图表-合同-季度） </p>
         * @author: wqm
         * @Param: year  当前年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.String>
         */
        Map<String, String> map = new HashMap<>();
        List<PlContractInfo> list = plContractInfoMapper.selectSummaryChartsContractQuarter(year);
        for (int i = 1; i < 5; i++) {
            map.put(String.valueOf(i), "0");
            int finalI = i;
            list.forEach(x -> {
                if (x.getSm().equals(String.valueOf(finalI))) {
                    map.put(String.valueOf(finalI), x.getContractAmount().toString());
                }
            });
        }
        return map;
    }

    /******************************************(收费)**************************************************************/
    @Override
    public Map<String, Object> getSummaryChartsFeeCollectedYear(int syear, int year) {
        /**
         * @Title: getSummaryChartsFeeCollectedYear
         * <p>Description:合同首页汇总信息（图表-实收-年） </p>
         * @author: wqm
         * @Param: syear 开始年份
         * @Param: year 结束年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.Object>
         */
        List<PlContractFeePlan> feePlans = plContractFeePlanMapper.selectSummaryChartsFeeCollectedYear(syear, year);
        Map<String, Object> map = new HashMap<>();
        for (int i = syear; i <= year; i++) {
            String key = String.valueOf(i);
            map.put(key, new PlContractFeePlan());
            int finalI = i;
            feePlans.forEach(x -> {
                if (x.getPlanTime() == finalI) {
                    map.put(key, x);
                }
            });
        }
        return map;
    }

    @Override
    public Map<String, Object> getSummaryChartsFeeCollectedMonth(int year) {
        /**
         * @Title: getSummaryChartsFeeCollectedMonth
         * <p>Description:合同首页汇总信息（图表-实收-月） </p>
         * @author: wqm
         * @Param: year 查询的年份
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.Object>
         */
        List<PlContractFeePlan> feePlans = plContractFeePlanMapper.getSummaryChartsFeeCollectedMonth(year);
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            String key = String.valueOf(i);
            map.put(key, new PlContractFeePlan());
            int finalI = i;
            feePlans.forEach(x -> {
                if (finalI == x.getPlanTime()) {
                    map.put(key, x);
                }
            });
        }
        return map;
    }

    @Override
    public Map<String, Object> getSummaryChartsFeeCollectedQuarter(int year) {
        /**
         * @Title: getSummaryChartsFeeCollectedQuarter
         * <p>Description:合同首页汇总信息（图表-实收-季） </p>
         * @author: wqm
         * @Param: year
         * @version 创建时间：2021/4/9
         * @return : java.util.Map<java.lang.String,java.lang.Object>
         */
        List<PlContractFeePlan> feePlans = plContractFeePlanMapper.getSummaryChartsFeeCollectedQuarter(year);
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            String key = String.valueOf(i);
            map.put(key, new PlContractFeePlan());
            int finalI = i;
            feePlans.forEach(x -> {
                if (x.getPlanTime() == finalI) {
                    map.put(key, x);
                }
            });
        }
        return map;
    }


    //统计合同金额-年
    //统计合同金额-季度
    //统计合同金额-月
    //统计实收金额-年
    //统计实收金额-季度
    //统计实收金额-月
}
