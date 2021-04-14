package com.ruoyi.project.projects.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.projects.mapper.PlProjectValueTemplateMapper;
import com.ruoyi.project.projects.domain.PlProjectValueTemplate;
import com.ruoyi.project.projects.service.IPlProjectValueTemplateService;

/**
 * 项目产值分配模板Service业务层处理
 * 
 * @author wqm
 * @date 2021-04-14
 */
@Service
public class PlProjectValueTemplateServiceImpl implements IPlProjectValueTemplateService 
{
    @Autowired
    private PlProjectValueTemplateMapper plProjectValueTemplateMapper;

    /**
     * 查询项目产值分配模板
     * 
     * @param tmeplateId 项目产值分配模板ID
     * @return 项目产值分配模板
     */
    @Override
    public PlProjectValueTemplate selectPlProjectValueTemplateById(String tmeplateId)
    {
        return plProjectValueTemplateMapper.selectPlProjectValueTemplateById(tmeplateId);
    }

    /**
     * 查询项目产值分配模板列表
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 项目产值分配模板
     */
    @Override
    public List<PlProjectValueTemplate> selectPlProjectValueTemplateList(PlProjectValueTemplate plProjectValueTemplate)
    {
        return plProjectValueTemplateMapper.selectPlProjectValueTemplateList(plProjectValueTemplate);
    }

    /**
     * 新增项目产值分配模板
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 结果
     */
    @Override
    public int insertPlProjectValueTemplate(PlProjectValueTemplate plProjectValueTemplate)
    {
        plProjectValueTemplate.setCreateTime(DateUtils.getNowDate());
        return plProjectValueTemplateMapper.insertPlProjectValueTemplate(plProjectValueTemplate);
    }

    /**
     * 修改项目产值分配模板
     * 
     * @param plProjectValueTemplate 项目产值分配模板
     * @return 结果
     */
    @Override
    public int updatePlProjectValueTemplate(PlProjectValueTemplate plProjectValueTemplate)
    {
        plProjectValueTemplate.setUpdateTime(DateUtils.getNowDate());
        return plProjectValueTemplateMapper.updatePlProjectValueTemplate(plProjectValueTemplate);
    }

    /**
     * 批量删除项目产值分配模板
     * 
     * @param tmeplateIds 需要删除的项目产值分配模板ID
     * @return 结果
     */
    @Override
    public int deletePlProjectValueTemplateByIds(String[] tmeplateIds)
    {
        return plProjectValueTemplateMapper.deletePlProjectValueTemplateByIds(tmeplateIds);
    }

    /**
     * 删除项目产值分配模板信息
     * 
     * @param tmeplateId 项目产值分配模板ID
     * @return 结果
     */
    @Override
    public int deletePlProjectValueTemplateById(String tmeplateId)
    {
        return plProjectValueTemplateMapper.deletePlProjectValueTemplateById(tmeplateId);
    }
}
