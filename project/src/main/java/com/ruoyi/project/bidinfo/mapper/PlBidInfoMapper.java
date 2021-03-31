package com.ruoyi.project.bidinfo.mapper;

import java.util.List;

import com.ruoyi.project.bidinfo.domain.PlBidInfo;

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

    List<PlBidInfo> selectAgents(Long userId);

    List<PlBidInfo> listCeaters();
}
