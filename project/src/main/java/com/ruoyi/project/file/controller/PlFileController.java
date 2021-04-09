package com.ruoyi.project.file.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.file.domain.PlFile;
import com.ruoyi.project.file.service.IPlFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的文档-文件Controller
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Api(tags = "A我的文档-文件")
@RestController
@RequestMapping("/project/file")
public class PlFileController extends BaseController {
    @Autowired
    private IPlFileService plFileService;

    /**
     * 查询我的文档-文件列表
     */
    @PreAuthorize("@ss.hasPermi('project:file:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询我的文档-文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plFile", value = "我的文档-文件查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlFile plFile) {
        startPage();
        List<PlFile> list = plFileService.selectPlFileList(plFile);
        return getDataTable(list);
    }

    /**
     * 导出我的文档-文件列表
     */
    @ApiOperation(value = "导出我的文档-文件列表")
    @PreAuthorize("@ss.hasPermi('project:file:export')")
    @Log(title = "我的文档-文件", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plFile", value = "我的文档-文件查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlFile plFile) {
        List<PlFile> list = plFileService.selectPlFileList(plFile);
        ExcelUtil<PlFile> util = new ExcelUtil<PlFile>(PlFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 获取我的文档-文件详细信息
     */
    @ApiOperation(value = "获取我的文档-文件详细信息")
    @PreAuthorize("@ss.hasPermi('project:file:query')")
    @GetMapping(value = "/{fileId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "我的文档-文件ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("fileId") String fileId) {
        return AjaxResult.success(plFileService.selectPlFileById(fileId));
    }

    /**
     * 新增我的文档-文件
     */
    @ApiOperation(value = "新增我的文档-文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plFile", value = "我的文档-文件对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:file:add')")
    @Log(title = "我的文档-文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlFile plFile) {
        return toAjax(plFileService.insertPlFile(plFile));
    }

    /**
     * 修改我的文档-文件
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plFile", value = "我的文档-文件对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改我的文档-文件")
    @PreAuthorize("@ss.hasPermi('project:file:edit')")
    @Log(title = "我的文档-文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlFile plFile) {
        return toAjax(plFileService.updatePlFile(plFile));
    }

    /**
     * 删除我的文档-文件
     */
    @ApiOperation(value = "删除我的文档-文件")
    @PreAuthorize("@ss.hasPermi('project:file:remove')")
    @Log(title = "我的文档-文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileIds", value = "我的文档-文件ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] fileIds) {
        return toAjax(plFileService.deletePlFileByIds(fileIds));
    }
}
