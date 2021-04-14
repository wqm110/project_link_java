package com.ruoyi.project.projects.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.projects.mapper.PlProjectsMapper;
import com.ruoyi.project.projects.domain.PlProjects;
import com.ruoyi.project.projects.service.IPlProjectsService;

/**
 * 项目基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-09
 */
@Service
public class PlProjectsServiceImpl implements IPlProjectsService 
{
    @Autowired
    private PlProjectsMapper plProjectsMapper;

    /**
     * 查询项目基本信息
     * 
     * @param projectId 项目基本信息ID
     * @return 项目基本信息
     */
    @Override
    public PlProjects selectPlProjectsById(Long projectId)
    {
        return plProjectsMapper.selectPlProjectsById(projectId);
    }

    /**
     * 查询项目基本信息列表
     * 
     * @param plProjects 项目基本信息
     * @return 项目基本信息
     */
    @Override
    public List<PlProjects> selectPlProjectsList(PlProjects plProjects)
    {
        return plProjectsMapper.selectPlProjectsList(plProjects);
    }

    /**
     * 新增项目基本信息
     * 
     * @param plProjects 项目基本信息
     * @return 结果
     */
    @Override
    public int insertPlProjects(PlProjects plProjects)
    {
        plProjects.setCreateTime(DateUtils.getNowDate());
        return plProjectsMapper.insertPlProjects(plProjects);
    }

    /**
     * 修改项目基本信息
     * 
     * @param plProjects 项目基本信息
     * @return 结果
     */
    @Override
    public int updatePlProjects(PlProjects plProjects)
    {
        plProjects.setUpdateTime(DateUtils.getNowDate());
        return plProjectsMapper.updatePlProjects(plProjects);
    }

    /**
     * 批量删除项目基本信息
     * 
     * @param projectIds 需要删除的项目基本信息ID
     * @return 结果
     */
    @Override
    public int deletePlProjectsByIds(Long[] projectIds)
    {
        return plProjectsMapper.deletePlProjectsByIds(projectIds);
    }

    /**
     * 删除项目基本信息信息
     * 
     * @param projectId 项目基本信息ID
     * @return 结果
     */
    @Override
    public int deletePlProjectsById(Long projectId)
    {
        return plProjectsMapper.deletePlProjectsById(projectId);
    }
}
