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
import com.ruoyi.project.projects.domain.PlProjectTasksendAssign;
import com.ruoyi.project.projects.service.IPlProjectTasksendAssignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务下达Controller
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "A任务下达")
@RestController
@RequestMapping("/project/tasksend")
public class PlProjectTasksendAssignController extends BaseController {
    @Autowired
    private IPlProjectTasksendAssignService plProjectTasksendAssignService;

    /**
     * 查询任务下达列表
     */
    @PreAuthorize("@ss.hasPermi('project:tasksend:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询任务下达列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectTasksendAssign" , value = "任务下达查询对象" , required = false, paramType = "query")
    })
    public TableDataInfo list(PlProjectTasksendAssign plProjectTasksendAssign) {
        startPage();
        List<PlProjectTasksendAssign> list = plProjectTasksendAssignService.selectPlProjectTasksendAssignList(plProjectTasksendAssign);
        return getDataTable(list);
    }

    /**
     * 导出任务下达列表
     */
    @ApiOperation(value = "导出任务下达列表")
    @PreAuthorize("@ss.hasPermi('project:tasksend:export')")
    @Log(title = "任务下达" , businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectTasksendAssign" , value = "任务下达查询对象" , required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlProjectTasksendAssign plProjectTasksendAssign) {
        List<PlProjectTasksendAssign> list = plProjectTasksendAssignService.selectPlProjectTasksendAssignList(plProjectTasksendAssign);
        ExcelUtil<PlProjectTasksendAssign> util = new ExcelUtil<PlProjectTasksendAssign>(PlProjectTasksendAssign.class);
        return util.exportExcel(list, "tasksend");
    }

    /**
     * 获取任务下达详细信息
     */
    @ApiOperation(value = "获取任务下达详细信息")
    @PreAuthorize("@ss.hasPermi('project:tasksend:query')")
    @GetMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" , value = "任务下达ID" , required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(plProjectTasksendAssignService.selectPlProjectTasksendAssignById(id));
    }

    /**
     * 新增任务下达
     */
    @ApiOperation(value = "新增任务下达")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectTasksendAssign" , value = "任务下达对象" , required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:tasksend:add')")
    @Log(title = "任务下达" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlProjectTasksendAssign plProjectTasksendAssign) {
        plProjectTasksendAssign.setCreateBy(SecurityUtils.getUsername());
        plProjectTasksendAssign.setId(IdUtils.snowLId().toString());
        plProjectTasksendAssign.setCreateTime(DateUtils.getNowDate());
        return toAjax(plProjectTasksendAssignService.insertPlProjectTasksendAssign(plProjectTasksendAssign));
    }

    /**
     * 修改任务下达
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plProjectTasksendAssign" , value = "任务下达对象" , required = true, paramType = "body")
    })
    @ApiOperation(value = "修改任务下达")
    @PreAuthorize("@ss.hasPermi('project:tasksend:edit')")
    @Log(title = "任务下达" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlProjectTasksendAssign plProjectTasksendAssign) {
        plProjectTasksendAssign.setUpdateBy(SecurityUtils.getUsername());
        plProjectTasksendAssign.setUpdateTime(DateUtils.getNowDate());
        return toAjax(plProjectTasksendAssignService.updatePlProjectTasksendAssign(plProjectTasksendAssign));
    }

    /**
     * 删除任务下达
     */
    @ApiOperation(value = "删除任务下达")
    @PreAuthorize("@ss.hasPermi('project:tasksend:remove')")
    @Log(title = "任务下达" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids" , value = "任务下达ID[]" , required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(plProjectTasksendAssignService.deletePlProjectTasksendAssignByIds(ids));
    }
}
