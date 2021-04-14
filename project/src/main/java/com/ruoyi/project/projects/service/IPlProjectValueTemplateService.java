package com.ruoyi.project.projects.service;

import java.util.List;
import com.ruoyi.project.projects.domain.PlProjectValueTemplate;

/**
 * 项目产值分配模板Service接口
 * 
 * @author wqm
 * @date 2021-04-14
 */
public interface IPlProjectValueTemplateService 
{
    /**
     * 查询项目产值分配模板
     * 
     * @param tmeplateId 项目产值分配模板ID
     * @return 项目产值分配模板
     */
    public PlProjectValueTemplate selectPlProjectValueTemplateById(String tmeplateId);

    /**
     * 查询项目产值分配模板列表
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 项目产值分配模板集合
     */
    public List<PlProjectValueTemplate> selectPlProjectValueTemplateList(PlProjectValueTemplate plProjectValueTemplate);

    /**
     * 新增项目产值分配模板
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 结果
     */
    public int insertPlProjectValueTemplate(PlProjectValueTemplate plProjectValueTemplate);

    /**
     * 修改项目产值分配模板
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 结果
     */
    public int updatePlProjectValueTemplate(PlProjectValueTemplate plProjectValueTemplate);

    /**
     * 批量删除项目产值分配模板
     * 
     * @param tmeplateIds 需要删除的项目产值分配模板ID
     * @return 结果
     */
    public int deletePlProjectValueTemplateByIds(String[] tmeplateIds);

    /**
     * 删除项目产值分配模板信息
     * 
     * @param tmeplateId 项目产值分配模板ID
     * @return 结果
     */
    public int deletePlProjectValueTemplateById(String tmeplateId);
}
