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
import com.ruoyi.project.projects.domain.PlProjectSchedule;
import com.ruoyi.project.projects.service.IPlProjectScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目进度计划Controller
 * 
 * @author ruoyi
 * @date 2021-04-14
 */
@Api(tags = "B项目进度计划")
@RestController
@RequestMapping("/project/schedule")
public class PlProjectScheduleController extends BaseController
{
    @Autowired
    private IPlProjectScheduleService plProjectScheduleService;

    /**
     * 查询项目进度计划列表
     */
    @PreAuthorize("@ss.hasPermi('project:schedule:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询项目进度计划列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectSchedule", value = "项目进度计划查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlProjectSchedule plProjectSchedule)
    {
        startPage();
        List<PlProjectSchedule> list = plProjectScheduleService.selectPlProjectScheduleList(plProjectSchedule);
        return getDataTable(list);
    }

    /**
     * 导出项目进度计划列表
     */
    @ApiOperation(value = "导出项目进度计划列表")
    @PreAuthorize("@ss.hasPermi('project:schedule:export')")
    @Log(title = "项目进度计划", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectSchedule", value = "项目进度计划查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlProjectSchedule plProjectSchedule)
    {
        List<PlProjectSchedule> list = plProjectScheduleService.selectPlProjectScheduleList(plProjectSchedule);
        ExcelUtil<PlProjectSchedule> util = new ExcelUtil<PlProjectSchedule>(PlProjectSchedule.class);
        return util.exportExcel(list, "schedule");
    }

    /**
     * 获取项目进度计划详细信息
     */
    @ApiOperation(value = "获取项目进度计划详细信息")
    @PreAuthorize("@ss.hasPermi('project:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleId", value = "项目进度计划ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("scheduleId") String scheduleId)
    {
        return AjaxResult.success(plProjectScheduleService.selectPlProjectScheduleById(scheduleId));
    }

    /**
     * 新增项目进度计划
     */
    @ApiOperation(value = "新增项目进度计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectSchedule", value = "项目进度计划对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:schedule:add')")
    @Log(title = "项目进度计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlProjectSchedule plProjectSchedule)
    {
        return toAjax(plProjectScheduleService.insertPlProjectSchedule(plProjectSchedule));
    }

    /**
     * 修改项目进度计划
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectSchedule", value = "项目进度计划对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改项目进度计划")
    @PreAuthorize("@ss.hasPermi('project:schedule:edit')")
    @Log(title = "项目进度计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlProjectSchedule plProjectSchedule)
    {
        return toAjax(plProjectScheduleService.updatePlProjectSchedule(plProjectSchedule));
    }

    /**
     * 删除项目进度计划
     */
    @ApiOperation(value = "删除项目进度计划")
    @PreAuthorize("@ss.hasPermi('project:schedule:remove')")
    @Log(title = "项目进度计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleIds", value = "项目进度计划ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] scheduleIds)
    {
        return toAjax(plProjectScheduleService.deletePlProjectScheduleByIds(scheduleIds));
    }
}
