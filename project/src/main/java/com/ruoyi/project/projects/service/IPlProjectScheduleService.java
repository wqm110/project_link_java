package com.ruoyi.project.projects.service;

import java.util.List;
import com.ruoyi.project.projects.domain.PlProjectSchedule;

/**
 * 项目进度计划Service接口
 * 
 * @author ruoyi
 * @date 2021-04-14
 */
public interface IPlProjectScheduleService 
{
    /**
     * 查询项目进度计划
     * 
     * @param scheduleId 项目进度计划ID
     * @return 项目进度计划
     */
    public PlProjectSchedule selectPlProjectScheduleById(String scheduleId);

    /**
     * 查询项目进度计划列表
     * 
     * @param plProjectSchedule 项目进度计划
     * @return 项目进度计划集合
     */
    public List<PlProjectSchedule> selectPlProjectScheduleList(PlProjectSchedule plProjectSchedule);

    /**
     * 新增项目进度计划
     * 
     * @param plProjectSchedule 项目进度计划
     * @return 结果
     */
    public int insertPlProjectSchedule(PlProjectSchedule plProjectSchedule);

    /**
     * 修改项目进度计划
     * 
     * @param plProjectSchedule 项目进度计划
     * @return 结果
     */
    public int updatePlProjectSchedule(PlProjectSchedule plProjectSchedule);

    /**
     * 批量删除项目进度计划
     * 
     * @param scheduleIds 需要删除的项目进度计划ID
     * @return 结果
     */
    public int deletePlProjectScheduleByIds(String[] scheduleIds);

    /**
     * 删除项目进度计划信息
     * 
     * @param scheduleId 项目进度计划ID
     * @return 结果
     */
    public int deletePlProjectScheduleById(String scheduleId);
}
