package com.ruoyi.project.projects.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.projects.mapper.PlProjectTasksendAssignMapper;
import com.ruoyi.project.projects.domain.PlProjectTasksendAssign;
import com.ruoyi.project.projects.service.IPlProjectTasksendAssignService;

/**
 * 任务下达Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class PlProjectTasksendAssignServiceImpl implements IPlProjectTasksendAssignService 
{
    @Autowired
    private PlProjectTasksendAssignMapper plProjectTasksendAssignMapper;

    /**
     * 查询任务下达
     * 
     * @param id 任务下达ID
     * @return 任务下达
     */
    @Override
    public PlProjectTasksendAssign selectPlProjectTasksendAssignById(String id)
    {
        return plProjectTasksendAssignMapper.selectPlProjectTasksendAssignById(id);
    }

    /**
     * 查询任务下达列表
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 任务下达
     */
    @Override
    public List<PlProjectTasksendAssign> selectPlProjectTasksendAssignList(PlProjectTasksendAssign plProjectTasksendAssign)
    {
        return plProjectTasksendAssignMapper.selectPlProjectTasksendAssignList(plProjectTasksendAssign);
    }

    /**
     * 新增任务下达
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 结果
     */
    @Override
    public int insertPlProjectTasksendAssign(PlProjectTasksendAssign plProjectTasksendAssign)
    {
        plProjectTasksendAssign.setCreateTime(DateUtils.getNowDate());
        return plProjectTasksendAssignMapper.insertPlProjectTasksendAssign(plProjectTasksendAssign);
    }

    /**
     * 修改任务下达
     * 
     * @param plProjectTasksendAssign 任务下达
     * @return 结果
     */
    @Override
    public int updatePlProjectTasksendAssign(PlProjectTasksendAssign plProjectTasksendAssign)
    {
        plProjectTasksendAssign.setUpdateTime(DateUtils.getNowDate());
        return plProjectTasksendAssignMapper.updatePlProjectTasksendAssign(plProjectTasksendAssign);
    }

    /**
     * 批量删除任务下达
     * 
     * @param ids 需要删除的任务下达ID
     * @return 结果
     */
    @Override
    public int deletePlProjectTasksendAssignByIds(String[] ids)
    {
        return plProjectTasksendAssignMapper.deletePlProjectTasksendAssignByIds(ids);
    }

    /**
     * 删除任务下达信息
     * 
     * @param id 任务下达ID
     * @return 结果
     */
    @Override
    public int deletePlProjectTasksendAssignById(String id)
    {
        return plProjectTasksendAssignMapper.deletePlProjectTasksendAssignById(id);
    }
}
