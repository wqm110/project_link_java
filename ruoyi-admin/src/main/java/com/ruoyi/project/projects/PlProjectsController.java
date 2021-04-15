package com.ruoyi.project.projects;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.projects.domain.PlProjects;
import com.ruoyi.project.projects.service.IPlProjectsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目基本信息Controller
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Api(tags = "B项目基本信息")
@RestController
@RequestMapping("/project/projects")
public class PlProjectsController extends BaseController {
    @Autowired
    private IPlProjectsService plProjectsService;

    /**
     * 查询项目基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:projects:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询项目基本信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjects" , value = "项目基本信息查询对象" , required = false, paramType = "query")
    })
    public TableDataInfo list(PlProjects plProjects) {
        startPage();
        List<PlProjects> list = plProjectsService.selectPlProjectsList(plProjects);
        return getDataTable(list);
    }

    /**
     * 导出项目基本信息列表
     */
    @ApiOperation(value = "导出项目基本信息列表")
    @PreAuthorize("@ss.hasPermi('project:projects:export')")
    @Log(title = "项目基本信息" , businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjects" , value = "项目基本信息查询对象" , required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlProjects plProjects) {
        List<PlProjects> list = plProjectsService.selectPlProjectsList(plProjects);
        ExcelUtil<PlProjects> util = new ExcelUtil<PlProjects>(PlProjects.class);
        return util.exportExcel(list, "projects");
    }

    /**
     * 获取项目基本信息详细信息
     */
    @ApiOperation(value = "获取项目基本信息详细信息")
    @PreAuthorize("@ss.hasPermi('project:projects:query')")
    @GetMapping(value = "/{projectId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId" , value = "项目基本信息ID" , required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId) {
        return AjaxResult.success(plProjectsService.selectPlProjectsById(projectId));
    }

    /**
     * 新增项目基本信息
     */
    @ApiOperation(value = "新增项目基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjects" , value = "项目基本信息对象（可关联多个合同）" , required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:projects:add')")
    @Log(title = "项目基本信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlProjects plProjects) {
        //TODO 验证合项目号重复
        //TODO 验证合同是否已被关联，可关联多个合同
        plProjects.setProjectId(IdUtils.snowLId());
        plProjects.setCreateBy(SecurityUtils.getUsername());
        return toAjax(plProjectsService.insertPlProjects(plProjects));
    }

    /**
     * 修改项目基本信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjects" , value = "项目基本信息对象" , required = true, paramType = "body")
    })
    @ApiOperation(value = "修改项目基本信息")
    @PreAuthorize("@ss.hasPermi('project:projects:edit')")
    @Log(title = "项目基本信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlProjects plProjects) {
        plProjects.setUpdateBy(SecurityUtils.getUsername());
        plProjects.setUpdateTime(DateUtils.getNowDate());
        return toAjax(plProjectsService.updatePlProjects(plProjects));
    }

    /**
     * 删除项目基本信息
     */
    @ApiOperation(value = "删除项目基本信息")
    @PreAuthorize("@ss.hasPermi('project:projects:remove')")
    @Log(title = "项目基本信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectIds" , value = "项目基本信息ID[]" , required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable Long[] projectIds) {
        return toAjax(plProjectsService.deletePlProjectsByIds(projectIds));
    }
}
