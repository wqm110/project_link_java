package com.ruoyi.project.projects.service;

import java.util.List;
import com.ruoyi.project.projects.domain.PlProjectTasksendAssign;

/**
 * 任务下达Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IPlProjectTasksendAssignService 
{
    /**
     * 查询任务下达
     * 
     * @param id 任务下达ID
     * @return 任务下达
     */
    public PlProjectTasksendAssign selectPlProjectTasksendAssignById(String id);

    /**
     * 查询任务下达列表
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 任务下达集合
     */
    public List<PlProjectTasksendAssign> selectPlProjectTasksendAssignList(PlProjectTasksendAssign plProjectTasksendAssign);

    /**
     * 新增任务下达
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 结果
     */
    public int insertPlProjectTasksendAssign(PlProjectTasksendAssign plProjectTasksendAssign);

    /**
     * 修改任务下达
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 结果
     */
    public int updatePlProjectTasksendAssign(PlProjectTasksendAssign plProjectTasksendAssign);

    /**
     * 批量删除任务下达
     * 
     * @param ids 需要删除的任务下达ID
     * @return 结果
     */
    public int deletePlProjectTasksendAssignByIds(String[] ids);

    /**
     * 删除任务下达信息
     * 
     * @param id 任务下达ID
     * @return 结果
     */
    public int deletePlProjectTasksendAssignById(String id);
}
