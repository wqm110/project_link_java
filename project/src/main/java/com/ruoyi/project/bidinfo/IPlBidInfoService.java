package com.ruoyi.project.bidinfo;

import com.ruoyi.project.bidinfo.domain.PlBidInfo;

import java.util.List;
import java.util.Map;


/**
 * 投标信息申请Service接口
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public interface IPlBidInfoService {
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
     * 批量删除投标信息申请
     *
     * @param bidIds 需要删除的投标信息申请ID
     * @return 结果
     */
    public int deletePlBidInfoByIds(Long[] bidIds);

    /**
     * 删除投标信息申请信息
     *
     * @param bidId 投标信息申请ID
     * @return 结果
     */
    public int deletePlBidInfoById(Long bidId);

    /**
     * 查询已录入的招标单位
     */
    List<PlBidInfo> slectBiddingUnits();

    /**
     * 查寻已录入的投标地点(下拉)
     */
    List<PlBidInfo> selectBidPlaces();

    /**
     * 本表全部代理人（登记人）
     */
    List<PlBidInfo> selectAgents();

    /**
     * 查询本表登记人
     */
    List<PlBidInfo> listCeaters();

    /**
     * 个人投标首页汇总信息
     */
    Map<String, Object> selectSummary(PlBidInfo info);


    List<PlBidInfo> auditList(PlBidInfo plBidInfo);

    /**
     * TODO 标书审查内容的提交
     * */
    int auditUpdate(PlBidInfo plBidInfo);
}
