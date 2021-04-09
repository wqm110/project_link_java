package com.ruoyi.project.bidinfo.mapper;

import com.ruoyi.project.bidinfo.domain.PlBidInfo;

import java.util.List;

/**
 * 投标信息申请Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public interface PlBidInfoMapper {
    /**
     * 查询投标信息申请
     *
     * @param bidId 投标信息申请ID
     * @return 投标信息申请
     */
    public PlBidInfo selectPlBidInfoById(Long bidId);

    /**
     * 查询投标信息申请列表
     *
     * @param plBidInfo 投标信息申请
     * @return 投标信息申请集合
     */
    public List<PlBidInfo> selectPlBidInfoList(PlBidInfo plBidInfo);

    /**
     * 新增投标信息申请
     *
     * @param plBidInfo 投标信息申请
     * @return 结果
     */
    public int insertPlBidInfo(PlBidInfo plBidInfo);

    /**
     * 修改投标信息申请
     *
     * @param plBidInfo 投标信息申请
     * @return 结果
     */
    public int updatePlBidInfo(PlBidInfo plBidInfo);

    /**
     * 删除投标信息申请
     *
     * @param bidId 投标信息申请ID
     * @return 结果
     */
    public int deletePlBidInfoById(Long bidId);

    /**
     * 批量删除投标信息申请
     *
     * @param bidIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlBidInfoByIds(Long[] bidIds);

    /**
     * 查询已录入的招标单位
     */
    List<PlBidInfo> slectBiddingUnits();

    /**
     * 查寻已录入的投标地点(下拉)
     */
    List<PlBidInfo> selectBidPlaces();

    /**
     * 查寻已录入的经办人
     */
    List<PlBidInfo> selectAgents();

    /**
     * 查寻已录入的创建人
     */
    List<PlBidInfo> listCreaters();

    /**
     * 某人今年/某年投标总数
     * 带有bid_date参数时查询投标年份
     * 带有projectManagerName 只查询本人 不带查询全部
     */
    int getContractCount(PlBidInfo info);

    /**
     * 今年/某年 中标
     * 带有winning_date参数时查询该年份的中标数
     * 带有 projectManagerName 只查询本人 不带查询全部
     */
    int getContractWins(PlBidInfo info);

    /**
     * 失标 统计
     */
    int getContractLosted(PlBidInfo info);
    /**
     * 投标总数
     */
    int selelectSumBidBond(PlBidInfo info);

    int selelectSumBidBondRecov(PlBidInfo info);

    /**
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
    int selelectSumformBond(PlBidInfo info);
}
