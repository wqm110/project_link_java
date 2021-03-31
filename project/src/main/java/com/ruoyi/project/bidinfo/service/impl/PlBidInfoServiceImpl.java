package com.ruoyi.project.bidinfo.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.bidinfo.mapper.PlBidInfoMapper;
import com.ruoyi.project.bidinfo.domain.PlBidInfo;
import com.ruoyi.project.bidinfo.service.IPlBidInfoService;

/**
 * 投标信息申请Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@Service
public class PlBidInfoServiceImpl implements IPlBidInfoService {
    @Autowired
    private PlBidInfoMapper plBidInfoMapper;

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
    public List<PlBidInfo> selectAgents(Long userId) {
        return plBidInfoMapper.selectAgents(userId);
    }

    @Override
    public List<PlBidInfo> listCeaters() {
        return plBidInfoMapper.listCeaters();
    }
}
