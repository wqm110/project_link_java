package com.ruoyi.project.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.PlProjectScheduleMapper;
import com.ruoyi.project.domain.PlProjectSchedule;
import com.ruoyi.project.service.IPlProjectScheduleService;

/**
 * 项目进度计划Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-25
 */
@Service
public class PlProjectScheduleServiceImpl implements IPlProjectScheduleService {
    @Autowired
    private PlProjectScheduleMapper plProjectScheduleMapper;

    /**
     * 查询项目进度计划
     *
     * @param scheduleId 项目进度计划ID
     * @return 项目进度计划
     */
    @Override
    public PlProjectSchedule selectPlProjectScheduleById(String scheduleId) {
        return plProjectScheduleMapper.selectPlProjectScheduleById(scheduleId);
    }

    /**
     * 查询项目进度计划列表
     *
     * @param plProjectSchedule 项目进度计划
     * @return 项目进度计划
     */
    @Override
    public List<PlProjectSchedule> selectPlProjectScheduleList(PlProjectSchedule plProjectSchedule) {
        return plProjectScheduleMapper.selectPlProjectScheduleList(plProjectSchedule);
    }

    /**
     * 新增项目进度计划
     *
     * @param plProjectSchedule 项目进度计划
     * @return 结果
     */
    @Override
    public int insertPlProjectSchedule(PlProjectSchedule plProjectSchedule) {
        plProjectSchedule.setCreateTime(DateUtils.getNowDate());
        return plProjectScheduleMapper.insertPlProjectSchedule(plProjectSchedule);
    }

    /**
     * 修改项目进度计划
     *
     * @param plProjectSchedule 项目进度计划
     * @return 结果
     */
    @Override
    public int updatePlProjectSchedule(PlProjectSchedule plProjectSchedule) {
        plProjectSchedule.setUpdateTime(DateUtils.getNowDate());
        return plProjectScheduleMapper.updatePlProjectSchedule(plProjectSchedule);
    }

    /**
     * 批量删除项目进度计划
     *
     * @param scheduleIds 需要删除的项目进度计划ID
     * @return 结果
     */
    @Override
    public int deletePlProjectScheduleByIds(String[] scheduleIds) {
        return plProjectScheduleMapper.deletePlProjectScheduleByIds(scheduleIds);
    }

    /**
     * 删除项目进度计划信息
     *
     * @param scheduleId 项目进度计划ID
     * @return 结果
     */
    @Override
    public int deletePlProjectScheduleById(String scheduleId) {
        return plProjectScheduleMapper.deletePlProjectScheduleById(scheduleId);
    }
}