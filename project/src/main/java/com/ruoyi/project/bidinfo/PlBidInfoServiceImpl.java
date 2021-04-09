package com.ruoyi.project.bidinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.bidinfo.domain.PlBidInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.bidinfo.mapper.PlBidInfoMapper;

/**
 * 投标信息申请Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@Service
public class PlBidInfoServiceImpl implements IPlBidInfoService {
    private PlBidInfoMapper plBidInfoMapper;

    @Autowired
    PlBidInfoServiceImpl(PlBidInfoMapper plBidInfoMapper) {
        this.plBidInfoMapper = plBidInfoMapper;
    }

    /**
     * 查询投标信息申请
     *
     * @param bidId 投标信息申请ID
     * @return 投标信息申请
     */
    @Override
    public PlBidInfo selectPlBidInfoById(Long bidId) {
        return plBidInfoMapper.selectPlBidInfoById(bidId);
    }

    /**
     * 查询投标信息申请列表
     *
     * @param plBidInfo 投标信息申请
     * @return 投标信息申请
     */
    @Override
    public List<PlBidInfo> selectPlBidInfoList(PlBidInfo plBidInfo) {
        return plBidInfoMapper.selectPlBidInfoList(plBidInfo);
    }

    /**
     * 新增投标信息申请
     *
     * @param plBidInfo 投标信息申请
     * @return 结果
     */
    @Override
    public int insertPlBidInfo(PlBidInfo plBidInfo) {
        plBidInfo.setCreateTime(DateUtils.getNowDate());
        plBidInfo.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        if (null == plBidInfo.getBidId())
            plBidInfo.setBidId(IdUtils.snowLId());
        return plBidInfoMapper.insertPlBidInfo(plBidInfo);
    }

    /**
     * 修改投标信息申请
     *
     * @param plBidInfo 投标信息申请
     * @return 结果
     */
    @Override
    public int updatePlBidInfo(PlBidInfo plBidInfo) {
        plBidInfo.setUpdateTime(DateUtils.getNowDate());
        return plBidInfoMapper.updatePlBidInfo(plBidInfo);
    }

    /**
     * 批量删除投标信息申请
     *
     * @param bidIds 需要删除的投标信息申请ID
     * @return 结果
     */
    @Override
    public int deletePlBidInfoByIds(Long[] bidIds) {
        return plBidInfoMapper.deletePlBidInfoByIds(bidIds);
    }

    /**
     * 删除投标信息申请信息
     *
     * @param bidId 投标信息申请ID
     * @return 结果
     */
    @Override
    public int deletePlBidInfoById(Long bidId) {
        return plBidInfoMapper.deletePlBidInfoById(bidId);
    }

    /**
     * 查询已录入的招标单位
     */
    @Override
    public List<PlBidInfo> slectBiddingUnits() {
        return plBidInfoMapper.slectBiddingUnits();
    }

    /**
     * 查寻已录入的投标地点(下拉)
     */
    @Override
    public List<PlBidInfo> selectBidPlaces() {
        return plBidInfoMapper.selectBidPlaces();
    }

    @Override
    public List<PlBidInfo> selectAgents() {
        return plBidInfoMapper.selectAgents();
    }

    /**
     * 查询已录入的合同的创建人
     */
    @Override
    public List<PlBidInfo> listCeaters() {
        return plBidInfoMapper.listCreaters();
    }

    /**
     * 汇总信息
     * TODO 不区分 人 4-8-2021
     */
    public Map<String, Object> selectSummary(PlBidInfo info) {
        Map<String, Object> summary = new HashMap<>();
//        info.setProjectManagerName("admin");
        /*
         * 某人今年/某年投标总数
         * 带有bid_date参数时查询投标年份
         * 带有projectManagerName 只查询本人 不带查询全部
         */
        int contractSum = plBidInfoMapper.getContractCount(info);
        /*
         * 今年/某年 中标
         * 带有winning_date参数时查询该年份的中标数
         * 带有 projectManagerName 只查询本人 不带查询全部
         */
        int contractWins = plBidInfoMapper.getContractWins(info);
        /*
         * 失标 统计
         */
        int contractLosted = plBidInfoMapper.getContractLosted(info);
        /*
         * 某人。 某年。 投标保证金总额
         */
        int SumBidBond = plBidInfoMapper.selelectSumBidBond(info);
        /*
         * 某人。 某年。 投标保证金回收总额
         */
        int SumBidBondRecov = plBidInfoMapper.selelectSumBidBondRecov(info);
        /*
         * 保证金已回收、未回收 统计
         * performBondRecovered
         * =======》没有设置 查询全部履约保证金
         * =======》= 0 未回收
         * =======》= 1 已回收
         * performBondTakebackDate
         * =======》指定了年份 查询某年的情况
         * =======》没指定值查询本年
         * projectManagerName
         * =======》有值 查询此人
         * =======》没值 查询全部人员
         */
        int SumformBond = plBidInfoMapper.selelectSumformBond(info);

        summary.put("contractSum", contractSum);
        summary.put("contractWins", contractWins);
        summary.put("contractLosted", contractLosted);
        summary.put("SumBidBond", SumBidBond);
        summary.put("SumBidBondRecov", SumBidBondRecov);
        summary.put("SumformBond", SumformBond);

        return summary;
    }

    /**
     * TODO 提交审批的列表
     * 审批状态不为0
     */
    @Override
    public List<PlBidInfo> auditList(PlBidInfo plBidInfo) {
        return new ArrayList<>();
    }

    /**
     * TODO 修改审批
     */
    @Override
    public int auditUpdate(PlBidInfo plBidInfo) {
        return 0;
    }
}
