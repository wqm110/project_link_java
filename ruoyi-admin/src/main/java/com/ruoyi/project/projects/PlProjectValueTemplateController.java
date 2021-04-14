package com.ruoyi.project.projects;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.ruoyi.project.projects.domain.PlProjectValueTemplate;
import com.ruoyi.project.projects.service.IPlProjectValueTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目产值分配模板Controller
 * 
 * @author wqm
 * @date 2021-04-14
 */
@Api(tags = "B项目产值分配模板")
@RestController
@RequestMapping("/project/template")
public class PlProjectValueTemplateController extends BaseController
{
    @Autowired
    private IPlProjectValueTemplateService plProjectValueTemplateService;

    /**
     * 查询项目产值分配模板列表
     */
    @PreAuthorize("@ss.hasPermi('project:template:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询项目产值分配模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectValueTemplate", value = "项目产值分配模板查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlProjectValueTemplate plProjectValueTemplate)
    {
        startPage();
        List<PlProjectValueTemplate> list = plProjectValueTemplateService.selectPlProjectValueTemplateList(plProjectValueTemplate);
        return getDataTable(list);
    }

    /**
     * 导出项目产值分配模板列表
     */
    @ApiOperation(value = "导出项目产值分配模板列表")
    @PreAuthorize("@ss.hasPermi('project:template:export')")
    @Log(title = "项目产值分配模板", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectValueTemplate", value = "项目产值分配模板查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlProjectValueTemplate plProjectValueTemplate)
    {
        List<PlProjectValueTemplate> list = plProjectValueTemplateService.selectPlProjectValueTemplateList(plProjectValueTemplate);
        ExcelUtil<PlProjectValueTemplate> util = new ExcelUtil<PlProjectValueTemplate>(PlProjectValueTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 获取项目产值分配模板详细信息
     */
    @ApiOperation(value = "获取项目产值分配模板详细信息")
    @PreAuthorize("@ss.hasPermi('project:template:query')")
    @GetMapping(value = "/{tmeplateId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tmeplateId", value = "项目产值分配模板ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("tmeplateId") String tmeplateId)
    {
        return AjaxResult.success(plProjectValueTemplateService.selectPlProjectValueTemplateById(tmeplateId));
    }

    /**
     * 新增项目产值分配模板
     */
    @ApiOperation(value = "新增项目产值分配模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectValueTemplate", value = "项目产值分配模板对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:template:add')")
    @Log(title = "项目产值分配模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlProjectValueTemplate plProjectValueTemplate)
    {
        return toAjax(plProjectValueTemplateService.insertPlProjectValueTemplate(plProjectValueTemplate));
    }

    /**
     * 修改项目产值分配模板
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectValueTemplate", value = "项目产值分配模板对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改项目产值分配模板")
    @PreAuthorize("@ss.hasPermi('project:template:edit')")
    @Log(title = "项目产值分配模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlProjectValueTemplate plProjectValueTemplate)
    {
        return toAjax(plProjectValueTemplateService.updatePlProjectValueTemplate(plProjectValueTemplate));
    }

    /**
     * 删除项目产值分配模板
     */
    @ApiOperation(value = "删除项目产值分配模板")
    @PreAuthorize("@ss.hasPermi('project:template:remove')")
    @Log(title = "项目产值分配模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tmeplateIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tmeplateIds", value = "项目产值分配模板ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] tmeplateIds)
    {
        return toAjax(plProjectValueTemplateService.deletePlProjectValueTemplateByIds(tmeplateIds));
    }
}
