package com.ruoyi.project.projects.service;

import java.util.List;
import com.ruoyi.project.projects.domain.PlProjects;

/**
 * 项目基本信息Service接口
 * 
 * @author ruoyi
 * @date 2021-04-09
 */
public interface IPlProjectsService 
{
    /**
     * 查询项目基本信息
     * 
     * @param projectId 项目基本信息ID
     * @return 项目基本信息
     */
    public PlProjects selectPlProjectsById(Long projectId);

    /**
     * 查询项目基本信息列表
     * 
     * @param plProjects 项目基本信息
     * @return 项目基本信息集合
     */
    public List<PlProjects> selectPlProjectsList(PlProjects plProjects);

    /**
     * 新增项目基本信息
     * 
     * @param plProjects 项目基本信息
     * @return 结果
     */
    public int insertPlProjects(PlProjects plProjects);

    /**
     * 修改项目基本信息
     * 
     * @param plProjects 项目基本信息
     * @return 结果
     */
    public int updatePlProjects(PlProjects plProjects);

    /**
     * 批量删除项目基本信息
     * 
     * @param projectIds 需要删除的项目基本信息ID
     * @return 结果
     */
    public int deletePlProjectsByIds(Long[] projectIds);

    /**
     * 删除项目基本信息信息
     * 
     * @param projectId 项目基本信息ID
     * @return 结果
     */
    public int deletePlProjectsById(Long projectId);
}
